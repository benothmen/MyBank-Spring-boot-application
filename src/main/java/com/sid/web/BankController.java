package com.sid.web;

import com.sid.Service.IBankMetier;
import com.sid.entity.Compte;
import com.sid.entity.Operation;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BankController {
    @Autowired
    private IBankMetier iBankMetier;

    @GetMapping("/operations")
    public String index(){
        return "comptes";
    }

    @GetMapping("/consulterCompte")
    public String consulterCompte(Model model, String codeCompte,
                                  @RequestParam(name = "page",defaultValue = "0") int page,
                                  @RequestParam(name = "size",defaultValue = "5") int size){
        model.addAttribute("codecompte",codeCompte);
        try {
            Compte cp=iBankMetier.consulterCompte(codeCompte);
            Page<Operation> pageOperation=iBankMetier.listOpreation(codeCompte,page,size);
            model.addAttribute("listoperation",pageOperation.getContent());
            int[] pages=new int[pageOperation.getTotalPages()];
            model.addAttribute("pages",pages);
            model.addAttribute("compte",cp);
        }catch (Exception ex){
            model.addAttribute("exception",ex);
        }

        return "comptes";
    }
    @PostMapping("/saveOperation")
    public String saveOperation(Model model,String typeOperation,String codecpt,double montant,String codeCompte2){
        try {
            if (typeOperation.equals("VERS")) {
                iBankMetier.verser(codecpt,montant);
            }else if (typeOperation.equals("RETR")){
                iBankMetier.retirer(codecpt,montant);
            }
            else {
                iBankMetier.virement(codecpt,codeCompte2,montant);
            }
        }catch (Exception e){
            model.addAttribute("error",e);
            return "redirect:/consulterCompte?codeCompte="+codecpt+"&error="+e.getMessage();
        }
        return "redirect:/consulterCompte?codeCompte="+codecpt;
    }

}
