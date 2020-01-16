package nl.hava.dapaj.bankapp.controller;

import nl.hava.dapaj.bankapp.model.SMEAccount;
import nl.hava.dapaj.bankapp.service.SMEAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.lang.Float.NaN;

@Controller
@Validated
public class SaldoController {

    @Value("your_secure_random_key_here")
    private String apiKey;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SMEAccountService smeAccountService;

    @CrossOrigin
    @PostMapping("/saldo")
    public @ResponseBody
    double getSector(@RequestParam String sector) {
        System.out.println("get sector");
        System.out.println("request data in: " + sector);
        System.out.println(smeAccountService.findsSmeAccountbyBranch(sector));
        return getSaldo(sector);
    }

    @CrossOrigin
    @PostMapping("/saldo_key")
    public @ResponseBody
    double getSaldoToken(@RequestParam String sector,@RequestHeader("Authorization") String authValue){
        System.out.println("data in = " + sector);
        System.out.println("request auth header: " + authValue);
        System.out.println("Key is: " + apiKey);
        String[] split = authValue.split(" ");
        if (split[0].equals("Bearer") && split[1].equals(apiKey)) {

        } else { // mag niet
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "No valid key found");
        }
        return getSaldo(sector);
    }
    private double getSaldo(@RequestParam String sector){
        double averageSaldo;
        try {
            List<SMEAccount> accountsByBranch = smeAccountService.findsSmeAccountbyBranch(sector);
            double totalSaldo = 0;
            for (SMEAccount account: accountsByBranch){
                if(account.getBranch().equals(sector))
                    totalSaldo += account.getBalance();
            }
            System.out.println("get saldo totaal saldo"+totalSaldo);
            System.out.println("getsaldo size" + accountsByBranch.size());

            int branchSize = accountsByBranch.size();
            if(branchSize == 0){
                branchSize = 1;
            }
            averageSaldo = totalSaldo/branchSize;
        } catch (EmptyResultDataAccessException ex) {
            System.out.println("get saldo: catch1");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "AdressPart niet gevonden", ex);
        } catch (Exception ex) {
            System.out.println("get saldo: catch2");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Thinks went wrong on our side, ex");
        }
        return averageSaldo;
    }

  /*  class SaldoMapper implements RowMapper<SMEAccount> {
        @Override
        public SMEAccount mapRow(ResultSet resultSet, int i) throws SQLException {
            return new SMEAccount(resultSet.getString("accountid"));
        }
    }*/
}
