package br.com.javamagazine.clinicajm.service;

import br.com.javamagazine.clinicajm.domain.Consulta;

import java.util.List;

public interface ConsultaService {

    void salvar(Consulta consulta);
    List<Consulta> recuperar();
    Consulta recuperarPorId(Long id);
    void atualizar(Consulta consulta);
    void excluir(long id);

}
