# Trabalho_Sistemas_Operacionais
Autores:

            Bruno Martins Alexandre - bmalexandre@inf.udpel.edu.br
            Gabriel Rosa de O. Silva - grosilva@inf.ufpel.edu.br

Nesse trabalho foi pedido que criassemos um programa (em C ou Java) que simulasse um dia agitado no estabelecimento de Kartódromo.
No kartódromo temos disponíveis karts e capacetes para todos que chegam. Cada pessoa precisa utilizar um capacete e um kart para poder entrar na pista.
O estabelecimento possui apenas 10 capacetes e 10 karts para todos os clientes.
A ideia principal é que cada pessoa a entrar na pista seja uma thread, nos colocando em um ambiente de programação concorrente.
a descrição mais detalhada do projeto pode ser vista na "Etapa 1" do arquivo _Trabalho SO.pdf_.

### Construção do projeto
Nós optamos por utilizar da linguagem Java, devido a sua maneira nativa de implementação de threads.
Criamos tres classes:  

 - **Pessoa** -> Contém os dados e detalhes de cada pessoa (thread) que chega para a fila de espera, para entrar no kartódromo;
 - **Tempo**  -> Contém os calculos de tempo para cada pessoa e piloto;
 - **Kartodrómo** -> Contém todos os métodos e dados de recursos, pilotos e pessoas dentro do estabelecimento, assim como as filas de espera e pessoas correndo a todo o tempo.


Ao final é gerado um arquivo "Relatório.txt" que armazena as informações de todo o período de 8 horas de funcionamento do dia.
