package br.com.javamagazine.clinicajm.dao;

import br.com.javamagazine.clinicajm.domain.Medico;
import br.com.javamagazine.clinicajm.domain.Consulta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConsultaDaoImpl implements ConsultaDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Consulta consulta) {
        em.persist(consulta);
    }

    @Override
    public List<Consulta> recuperar() {
        // Usando JOIN FETCH para carregar os dados relacionados de Medico e Paciente
        return em.createQuery(
                "select c from Consulta c " +
                        "join fetch c.medico " +
                        "join fetch c.paciente", Consulta.class).getResultList();
    }

    @Override
    public Consulta recuperarPorID(long id) {
        return em.find(Consulta.class, id);
    }

    @Override
    public void atualizar(Consulta consulta) {
        em.merge(consulta);
    }

    @Override
    public void excluir(long id) {
        em.remove(em.getReference(Consulta.class, id));
    }


}
