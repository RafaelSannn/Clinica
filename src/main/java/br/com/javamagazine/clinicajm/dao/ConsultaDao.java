package br.com.javamagazine.clinicajm.dao;

import br.com.javamagazine.clinicajm.domain.Consulta;
import br.com.javamagazine.clinicajm.domain.Medico;

import java.util.List;

public interface ConsultaDao {

    void salvar(Consulta consulta);
    List<Consulta> recuperar();
    Consulta recuperarPorID(long id);
    void atualizar(Consulta consulta);
    void excluir(long id);

}
