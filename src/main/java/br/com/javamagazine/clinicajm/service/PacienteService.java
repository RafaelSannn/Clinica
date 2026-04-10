package br.com.javamagazine.clinicajm.service;

import br.com.javamagazine.clinicajm.domain.Paciente;
import java.util.List;

public interface PacienteService {
    void salvar(Paciente paciente);
    List<Paciente> recuperar();
    Paciente recuperarPorId(Long id);
    void atualizar(Paciente paciente);
    void excluir(long id);
    List<Paciente> buscarPorNome(String termo); // Novo método
}