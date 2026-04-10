package br.com.javamagazine.clinicajm.service;

import br.com.javamagazine.clinicajm.dao.PacienteDao;
import br.com.javamagazine.clinicajm.domain.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteDao pacienteDao;

    @Override
    public void salvar(Paciente paciente) {
        pacienteDao.salvar(paciente);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> recuperar() {
        return pacienteDao.recuperar();
    }

    @Override
    @Transactional(readOnly = true)
    public Paciente recuperarPorId(Long id) {
        return pacienteDao.recuperarPorID(id);
    }

    @Override
    public void atualizar(Paciente paciente) {
        pacienteDao.atualizar(paciente);
    }

    @Override
    public void excluir(long id) {
        pacienteDao.excluir(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> buscarPorNome(String termo) {
        return pacienteDao.findByNomeContainingIgnoreCase(termo);
    }
}