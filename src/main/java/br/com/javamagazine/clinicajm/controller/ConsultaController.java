package br.com.javamagazine.clinicajm.controller;

import br.com.javamagazine.clinicajm.domain.Consulta;
import br.com.javamagazine.clinicajm.domain.Medico;
import br.com.javamagazine.clinicajm.domain.Paciente;
import br.com.javamagazine.clinicajm.domain.enums.Especialidade;
import br.com.javamagazine.clinicajm.service.ConsultaService;
import br.com.javamagazine.clinicajm.service.MedicoService;
import br.com.javamagazine.clinicajm.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private MedicoService medicoService;

    // Lista todas as consultas
    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("consultas", consultaService.recuperar());
        return "consulta/list_consulta";
    }

    // Formulário para agendar consulta
    @GetMapping("/cadastro")
    public String mostrarFormulario(Model model) {
        model.addAttribute("consulta", new Consulta());
        model.addAttribute("especialidades", Especialidade.values());
        return "consulta/add_consulta";
    }

    // Salva a consulta
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Consulta consulta) {
        consultaService.salvar(consulta);
        return "redirect:/consultas/listar";
    }

    // Atendimento da consulta
    @GetMapping("/{id}/atender")
    public String atenderConsulta(@PathVariable("id") Long id, Model model) {
        Consulta consulta = consultaService.recuperarPorId(id);
        if (consulta == null) {
            model.addAttribute("mensagem", "Consulta não encontrada.");
            return "redirect:/consultas/listar";
        }
        model.addAttribute("consulta", consulta);
        return "consulta/atendimento_consulta";
    }

    // Buscar pacientes por nome (AJAX)
    @GetMapping("/pacientesPorNome")
    @ResponseBody
    public List<Paciente> buscarPacientesPorNome(@RequestParam("termo") String termo) {
        return pacienteService.buscarPorNome(termo);
    }

    // Buscar médicos por especialidade (AJAX)
    @GetMapping("/medicosPorEspecialidade")
    @ResponseBody
    public List<Medico> buscarMedicosPorEspecialidade(@RequestParam("especialidade") Especialidade especialidade) {
        return medicoService.recuperarPorEspecialidade(especialidade);
    }
}
