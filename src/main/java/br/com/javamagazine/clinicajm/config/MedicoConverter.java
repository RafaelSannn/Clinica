package br.com.javamagazine.clinicajm.config;

import br.com.javamagazine.clinicajm.domain.Medico;
import br.com.javamagazine.clinicajm.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MedicoConverter implements Converter<String, Medico> {

    @Autowired
    private MedicoService medicoService;

    @Override
    public Medico convert(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        // Pega o número que veio da tela e busca o objeto inteiro no banco
        return medicoService.recuperarPorId(Long.parseLong(id));
    }
}