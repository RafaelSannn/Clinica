package br.com.javamagazine.clinicajm.controller;

import br.com.javamagazine.clinicajm.domain.Medico;
import br.com.javamagazine.clinicajm.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;


    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("medicos", medicoService.recuperar());
        return new ModelAndView("/medico/list_medico", model);
    }

    @GetMapping("/cadastro")
    public String preSalvar(@ModelAttribute("medico") Medico medico){
        return "/medico/add_medico";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("medico") Medico medico, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/medico/add_medico";
        }

        medicoService.salvar(medico);
        attr.addFlashAttribute("mensagem", "Médico cadastrado com sucesso.");
        return "redirect:/medicos/listar";
    }

    @GetMapping("/{id}/atualizar")
    public ModelAndView preAtualizar(@PathVariable("id") long id, ModelMap model) {
        Medico medico = medicoService.recuperarPorId(id);
        model.addAttribute("medico", medico);
        return new ModelAndView("/medico/add_medico", model);
    }

    @PutMapping("/salvar")
    public String atualizar(@Valid @ModelAttribute("medico") Medico medico, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/medico/add_medico";
        }

        medicoService.atualizar(medico);
        attr.addFlashAttribute("mensagem", "Médico atualizado com sucesso.");
        return "redirect:/medicos/listar";
    }

    @GetMapping("/{id}/remover")
    public String remover(@PathVariable("id") long id, RedirectAttributes attr) {
        medicoService.excluir(id);
        attr.addFlashAttribute("mensagem", "Médico excluído com sucesso.");
        return "redirect:/medicos/listar";
    }

}
