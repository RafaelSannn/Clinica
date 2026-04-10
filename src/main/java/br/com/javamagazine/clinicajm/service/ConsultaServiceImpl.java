package br.com.javamagazine.clinicajm.service;

import br.com.javamagazine.clinicajm.dao.ConsultaDao;
import br.com.javamagazine.clinicajm.domain.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ConsultaServiceImpl implements ConsultaService {

    @Autowired
    private ConsultaDao consultaDao;

    @Override
    public void salvar(Consulta consulta) {
        consultaDao.salvar(consulta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Consulta> recuperar() {
        return consultaDao.recuperar();
    }

    @Override
    @Transactional(readOnly = true)
    public Consulta recuperarPorId(Long id) {
        return consultaDao.recuperarPorID(id);
    }

    @Override
    public void atualizar(Consulta consulta) {
        consultaDao.atualizar(consulta);
    }

    @Override
    public void excluir(long id) {
        consultaDao.excluir(id);
    }
}
