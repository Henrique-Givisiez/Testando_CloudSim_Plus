# ☁️ CloudSim Plus – Micro-testes

## O que é o CloudSim Plus?

O CloudSim Plus é um **simulador de computação em nuvem escrito em Java**, usado para estudar e modelar o comportamento de **datacenters, VMs, tarefas (cloudlets)** e **políticas de escalonamento** - sem precisar de infraestrutura real. É muito usado em pesquisas sobre **cloud computing, FinOps, EDoS, autoscaling e alocação de recursos**.

---

## Testes implementados

### 🔹 Teste A — “2 VMs executando 1 cloudlet”
Compara o desempenho de duas VMs (1.000 e 2.000 MIPS) executando a mesma tarefa de 10.000 MI.  
Resultado esperado: a VM mais rápida termina cerca de 2x antes.

### 🔹 Teste B — “Schedulers”
Compara os modos de escalonamento dentro de uma VM:
- **TimeShared** → tarefas dividem CPU e terminam quase juntas.
- **SpaceShared** → tarefas são executadas em fila, uma após a outra.

---

##  Como executar

1. Clone este repositório e abra no **IntelliJ IDEA** ou outro IDE Java.
2. Certifique-se de ter o **JDK 25** instalado.
3. Rode um dos testes:
   ```bash
   ./gradlew run
   ```
   
---

## Saída Esperada (Exemplo Teste B)
    
```
VM 0 (1000 MIPS) -> Cloudlet 0 | start=0.10 | finish=10.10 | exec=10.00
VM 1 (2000 MIPS) -> Cloudlet 1 | start=0.10 | finish=5.10  | exec=5.00

=== TimeShared ===
Cloudlet 0 | start=0.10 | finish=20.00 | exec=10.00
Cloudlet 1 | start=0.10 | finish=20.00 | exec=10.00

=== SpaceShared ===
Cloudlet 0 | start=0.10 | finish=10.10 | exec=10.00
Cloudlet 1 | start=10.10 | finish=20.10 | exec=10.00
```

---

## Repositório CloudSim Plus
Link: https://github.com/manoelcampos/cloudsimplus