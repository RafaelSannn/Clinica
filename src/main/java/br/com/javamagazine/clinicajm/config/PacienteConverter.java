package br.com.javamagazine.clinicajm.config;

import br.com.javamagazine.clinicajm.domain.Paciente;
import br.com.javamagazine.clinicajm.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PacienteConverter implements Converter<String, Paciente> {

    @Autowired
    private PacienteService pacienteService;

    @Override
    public Paciente convert(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        // Pega o número que veio da tela e busca o objeto inteiro no banco
        return pacienteService.recuperarPorId(Long.parseLong(id));
    }
}