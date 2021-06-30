package br.com.letscode.service;

import br.com.letscode.annotationLiteral.ContaAnnotationLiteral;
import br.com.letscode.dominio.ContaEnum;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

public class ContaFactory {

    @Inject
    @Any
    private Instance<ContaService> contaServiceInstance;

    public ContaService createConta(ContaEnum contaEnum) {
        final ContaAnnotationLiteral literal = new ContaAnnotationLiteral(contaEnum);
        return contaServiceInstance.select(literal).get();
    }
}
