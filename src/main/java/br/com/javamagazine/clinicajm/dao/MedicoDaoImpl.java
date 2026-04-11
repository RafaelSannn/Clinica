package br.com.javamagazine.clinicajm.dao;

import br.com.javamagazine.clinicajm.domain.Medico;
import br.com.javamagazine.clinicajm.domain.enums.Especialidade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class MedicoDaoImpl implements MedicoDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void salvar(Medico medico) {
        em.persist(medico);
    }

    @Override
    public List<Medico> recuperar() {
        return em.createQuery("select m from Medico m", Medico.class).getResultList();
    }

    @Override
    public Medico recuperarPorID(long id) {
        return em.find(Medico.class, id);
    }

    @Override
    public void atualizar(Medico medico) {
        // Necessário para sincronizar estado de entidades destacadas
        em.merge(medico);
    }

    @Override
    public void excluir(long id) {
        // getReference evita uma consulta SELECT desnecessária antes do DELETE
        em.remove(em.getReference(Medico.class, id));
    }

    @Override
    public List<Medico> findByEspecialidade(Especialidade especialidade) {
        // Agora m.especialidade e o parâmetro são ambos do tipo Especialidade (Enum)
        return em.createQuery(
                        "SELECT m FROM Medico m WHERE m.especialidade = :especialidade",
                        Medico.class)
                .setParameter("especialidade", especialidade)
                .getResultList();
    }
}