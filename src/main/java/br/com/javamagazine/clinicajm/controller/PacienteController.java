package br.com.javamagazine.clinicajm.controller;

import br.com.javamagazine.clinicajm.domain.Paciente;
import br.com.javamagazine.clinicajm.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("pacientes", pacienteService.recuperar());
        return new ModelAndView("/paciente/list_paciente", model);
    }

    @GetMapping("/cadastro")
    public String preSalvar(@ModelAttribute("paciente") Paciente paciente) {
        return "/paciente/add_paciente";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("paciente") Paciente paciente, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/paciente/add_paciente";
        }

        pacienteService.salvar(paciente);
        attr.addFlashAttribute("mensagem", "Paciente cadastrado com sucesso.");
        return "redirect:/pacientes/listar";
    }

    @GetMapping("/{id}/atualizar")
    public ModelAndView preAtualizar(@PathVariable("id") long id, ModelMap model) {
        Paciente paciente = pacienteService.recuperarPorId(id);
        model.addAttribute("paciente", paciente);
        return new ModelAndView("/paciente/add_paciente", model);
    }

    @PutMapping("/salvar")
    public String atualizar(@Valid @ModelAttribute("paciente") Paciente paciente, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/paciente/add_paciente";
        }

        pacienteService.atualizar(paciente);
        attr.addFlashAttribute("mensagem", "Paciente atualizada com sucesso.");
        return "redirect:/pacientes/listar";
    }

    @GetMapping("/{id}/remover")
    public String remover(@PathVariable("id") long id, RedirectAttributes attr) {
        pacienteService.excluir(id);
        attr.addFlashAttribute("mensagem", "Paciente excluído com sucesso.");
        return "redirect:/pacientes/listar";
    }

}
