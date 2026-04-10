package br.com.javamagazine.clinicajm.dao;

import br.com.javamagazine.clinicajm.domain.Medico;
import br.com.javamagazine.clinicajm.domain.enums.Especialidade;
import java.util.List;

public interface MedicoDao {
    void salvar(Medico medico);
    List<Medico> recuperar();
    Medico recuperarPorID(long id);
    void atualizar(Medico medico);
    void excluir(long id);
    List<Medico> findByEspecialidade(Especialidade especialidade); // Novo método
}