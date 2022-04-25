package com.kodigo.nftmarketplace.controllers;

import com.kodigo.nftmarketplace.dtos.users.UserLoginDTO;
import com.kodigo.nftmarketplace.dtos.users.UsersCreationDTO;
import com.kodigo.nftmarketplace.models.UserConstraints;
import com.kodigo.nftmarketplace.models.Users;
import com.kodigo.nftmarketplace.services.UserConstraintsService;
import com.kodigo.nftmarketplace.services.UsersService;
import com.kodigo.nftmarketplace.utilities.JwtUtil;
import com.kodigo.nftmarketplace.utilities.Parser;
import com.kodigo.nftmarketplace.utilities.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @Autowired
    UserConstraintsService constraintsService;

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("/get")
    public ArrayList<Users> getUsers(){
        return usersService.getUsers();
    }

    @PostMapping("/login")
    public ResponseEntity getUserById(@Valid @RequestBody UserLoginDTO userLoginDTO){
        try{

            Users user = usersService.getByUsername(userLoginDTO.getUsername());

            if(user == null || user.getUserstatus().equals("Inactive"))
                return ResponseEntity.badRequest().body("User invalid");

            UserConstraints constraint = constraintsService.getConstraintByUser(user.getUserid());

            HashMap<String, String> hash = hashPassword(userLoginDTO.getPassword(), constraint.getConstraintvalue());

            if(!user.getPassword().equals(hash.get("password")))
                return ResponseEntity.badRequest().body("Invalid credentials");

            String token = jwtUtil.generateToken(user);

            HashMap<String, String> map = new HashMap<>();

            map.put("Auth", token);

            return ResponseEntity.ok(map);

        }catch (Exception ex){

            return ResponseEntity.internalServerError().body("");
        }
    }

    @PostMapping("/create")
    public Users createUser(@RequestBody Users user){

        return usersService.createUser(user);
    }

    @PostMapping("/create/customer")
    public ResponseEntity createCustomer(@Valid @RequestBody UsersCreationDTO usersCreationDTO){

        try{

            //To validate if username is available
            if(usersService.existsByUsername(usersCreationDTO.getUsername()))
                return ResponseEntity.badRequest().body("Username not available");

            //To validate if email is available
            if(usersService.existsByEmail(usersCreationDTO.getEmail()))
                return ResponseEntity.badRequest().body("Email not available");

            //To validate if password is valid
            if(!Validators.isValidPassword(usersCreationDTO.getPassword()))
                return ResponseEntity.badRequest().body("Password is invalid");

            HashMap<String, String> hash = hashPassword(usersCreationDTO.getPassword(), "");

            usersCreationDTO.setPassword(hash.get("password"));

            //Parsing UserCreationDTO to Users
            Users user = Parser.userCreationDTOToUsers(usersCreationDTO);

            user.setUserstatus("Active");
            user.setUsertype("Customer");

            user = usersService.createUser(user);

            UserConstraints constraints = new UserConstraints();

            constraints.setConstraintname("salt");
            constraints.setConstraintvalue(hash.get("salt"));
            constraints.setUserid(user.getUserid());

            constraintsService.createConstraint(constraints);

            return ResponseEntity.ok("");

        }catch (Exception e){

            return ResponseEntity.internalServerError().body("");
        }

    }

    @PostMapping("/create/investor")
    public ResponseEntity createInvestor(@Valid @RequestBody UsersCreationDTO usersCreationDTO){
        try{

            //To validate if username is available
            if(usersService.existsByUsername(usersCreationDTO.getUsername()))
                return ResponseEntity.badRequest().body("Username not available");

            //To validate if email is available
            if(usersService.existsByEmail(usersCreationDTO.getEmail()))
                return ResponseEntity.badRequest().body("Email not available");

            //To validate if password is valid
            if(!Validators.isValidPassword(usersCreationDTO.getPassword()))
                return ResponseEntity.badRequest().body("Password is invalid");

            HashMap<String, String> hash = hashPassword(usersCreationDTO.getPassword(), "");

            usersCreationDTO.setPassword(hash.get("password"));

            //Parsing UserCreationDTO to Users
            Users user = Parser.userCreationDTOToUsers(usersCreationDTO);

            user.setUserstatus("Active");
            user.setUsertype("Investor");

            user = usersService.createUser(user);

            UserConstraints constraints = new UserConstraints();

            constraints.setConstraintname("salt");
            constraints.setConstraintvalue(hash.get("salt"));
            constraints.setUserid(user.getUserid());

            constraintsService.createConstraint(constraints);

            return ResponseEntity.ok("");

        }catch (Exception e){

            return ResponseEntity.internalServerError().body("");
        }
    }

    public HashMap<String, String> hashPassword(String rawPassword, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {

        byte[] rawsalt;

        if(salt.isEmpty()){

            rawsalt = new byte[16];
            SecureRandom random = new SecureRandom();
            random.nextBytes(rawsalt);
        }else{

            rawsalt = Base64.getDecoder().decode(salt);
        }

        KeySpec spec = new PBEKeySpec(rawPassword.toCharArray(), rawsalt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = factory.generateSecret(spec).getEncoded();

        HashMap<String, String> pass = new HashMap<>();

        pass.put("password", Base64.getEncoder().encodeToString(hash));
        pass.put("salt", Base64.getEncoder().encodeToString(rawsalt));

        return pass;
    }


}
