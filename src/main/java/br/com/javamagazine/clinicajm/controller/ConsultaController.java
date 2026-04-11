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

import java.time.LocalDateTime;
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

    // Listagem geral de consultas
    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("consultas", consultaService.recuperar());
        return "consulta/list_consulta";
    }

    // Abre formulário para novo agendamento
    @GetMapping("/cadastro")
    public String mostrarFormulario(Model model) {
        model.addAttribute("consulta", new Consulta());
        model.addAttribute("especialidades", Especialidade.values());
        return "consulta/add_consulta";
    }

    // Carrega dados para editar um agendamento existente
    @GetMapping("/{id}/editar")
    public String preEditar(@PathVariable("id") Long id, Model model) {
        Consulta consulta = consultaService.recuperarPorId(id);
        model.addAttribute("consulta", consulta);
        model.addAttribute("especialidades", Especialidade.values());
        return "consulta/add_consulta";
    }

    // Salva agendamento inicial ou alteração de data/sintomas
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Consulta consulta) {
        if (consulta.getId() == 0) {
            consultaService.salvar(consulta);
        } else {
            consultaService.atualizar(consulta);
        }
        return "redirect:/consultas/listar";
    }

    // Remove registro de consulta
    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable("id") Long id) {
        consultaService.excluir(id);
        return "redirect:/consultas/listar";
    }

    // Abre tela para o médico realizar o atendimento (preencher receita/avaliação)
    @GetMapping("/{id}/atender")
    public String atenderConsulta(@PathVariable("id") Long id, Model model) {
        Consulta consulta = consultaService.recuperarPorId(id);
        if (consulta == null) {
            return "redirect:/consultas/listar";
        }
        model.addAttribute("consulta", consulta);
        return "consulta/atendimento_consulta";
    }

    // Processa o fechamento do atendimento médico
    @PostMapping("/atendimento/salvar")
    public String salvarAtendimento(@ModelAttribute Consulta consulta) {
        // Busca a consulta no banco para garantir que não perderemos o vínculo com médico e paciente
        Consulta c = consultaService.recuperarPorId(consulta.getId());

        // Atualiza apenas os campos preenchidos na tela de atendimento
        c.setReceita(consulta.getReceita());
        c.setAvaliacao_atendimento(consulta.getAvaliacao_atendimento());
        c.setData_atendimento(LocalDateTime.now()); // Marca o horário exato da conclusão

        consultaService.atualizar(c);
        return "redirect:/consultas/listar";
    }

    // Endpoints para chamadas AJAX no frontend
    @GetMapping("/pacientesPorNome")
    @ResponseBody
    public List<Paciente> buscarPacientesPorNome(@RequestParam("termo") String termo) {
        return pacienteService.buscarPorNome(termo);
    }

    @GetMapping("/medicosPorEspecialidade")
    @ResponseBody
    public List<Medico> buscarMedicosPorEspecialidade(@RequestParam("especialidade") Especialidade especialidade) {
        return medicoService.recuperarPorEspecialidade(especialidade);
    }
}