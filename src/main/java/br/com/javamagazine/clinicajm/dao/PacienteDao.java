package br.com.javamagazine.clinicajm.dao;

import br.com.javamagazine.clinicajm.domain.Paciente;
import java.util.List;

public interface PacienteDao {
    void salvar(Paciente paciente);
    List<Paciente> recuperar();
    Paciente recuperarPorID(long id);
    void atualizar(Paciente paciente);
    void excluir(long id);
    List<Paciente> findByNomeContainingIgnoreCase(String nome); // Novo método
}