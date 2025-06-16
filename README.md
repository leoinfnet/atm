# 💸 Projeto ATM - API de Caixa Eletrônico

Este projeto simula uma API REST de um Caixa Eletrônico (ATM), implementada em **Java** com o framework **Javalin**.

Ele foi criado para fins educacionais e é usado como base para provas práticas. O projeto contém intencionalmente **pontos a serem refatorados**, como más práticas de modelagem, segurança e testes.

---

## 🚀 Como executar localmente

### 🔧 Requisitos

- Java 17+
- Maven 3.8+
- Git

### 📥 Clonando o projeto

Você pode **fazer fork** do repositório e depois clonar para sua máquina:

```bash
# Faça o fork no GitHub e copie sua URL
git clone https://github.com/seu-usuario/projeto-atm.git
cd projeto-atm
```

> ✅ Ou clone direto do repositório original (se estiver apenas testando):

```bash
git clone https://github.com/prof-leonardo-gloria/projeto-atm.git
cd projeto-atm
```

### ▶️ Rodando o projeto

O projeto é uma aplicação simples que roda em modo standalone com `main()`:

```bash
mvn clean compile exec:java -Dexec.mainClass="br.com.infnet.App"
```

A API ficará disponível em:  
🔗 http://localhost:9090

---

## 📚 Endpoints disponíveis

| Método | Rota                             | Descrição                  |
|--------|----------------------------------|----------------------------|
| POST   | `/contas`                        | Criar nova conta           |
| POST   | `/contas/{numero}/depositar`     | Depositar valor            |
| GET    | `/contas/{numero}/saldo`         | Consultar saldo            |
| GET    | `/contas`                        | Listar todas as contas     |

---

## 🧪 Executando os testes

O projeto contém testes unitários e de integração usando:

- JUnit 5
- Rest Assured
- Hamcrest

Para rodar os testes:

```bash
mvn test
```

---

## 🧠 Desafios propostos (prova prática)

- Refatorar a classe `Conta` para melhorar segurança e design
- Criar a classe `ServicoCaixa` com lógica de saque
- Escrever testes com JUnit e Rest Assured

---

## 🤝 Contribuição

Quer praticar mais?

1. Faça um fork do projeto
2. Crie uma branch: `git checkout -b minha-solucao`
3. Faça suas alterações
4. Commit: `git commit -m "Minha solução"`
5. Push: `git push origin minha-solucao`

---

## 📄 Licença

Projeto educacional distribuído sem fins comerciais.
