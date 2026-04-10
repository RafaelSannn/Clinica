package br.com.javamagazine.clinicajm.service;

import br.com.javamagazine.clinicajm.domain.Medico;
import br.com.javamagazine.clinicajm.domain.enums.Especialidade;
import java.util.List;

public interface MedicoService {
    void salvar(Medico medico);
    List<Medico> recuperar();
    Medico recuperarPorId(Long id);
    void atualizar(Medico medico);
    void excluir(long id);
    List<Medico> recuperarPorEspecialidade(Especialidade especialidade); // Novo método
}