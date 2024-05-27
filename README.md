# Gráfico-MetasESG

O Gráficos-MetasESG é uma aplicação JavaFX desenvolvida como projeto acadêmico para a disciplina de Lógica de Programação a Objetos (LOO) na UNIP Marques de São Vicente. A ferramenta visa auxiliar na análise de dados relacionados aos critérios Meio Ambiente, Sociais e de Governança (ESG), de forma interativa e eficaz, por meio de gráficos e visualizações customizáveis.

<!-- TOC -->
* [📑 Recursos](#-recursos)
* [🛠 Arquitetura](#-arquitetura)
* [🚀 Instalação](#-instalação)
* [☕ Contribuição](#-contribuição)
* [💻 Telas](#-telas)
* [📚 Tecnologias](#-tecnologias)
* [🚧 Status do Projeto](#-status-do-projeto)
* [📫 Autores](#-autores)
* [📹 Tutorial](#-tutorial)
<!-- TOC -->

## 📑 [Recursos](#-recursos)

- **Interface de Usuário Intuitiva:** Interface de usuário moderna e amigável, projetada para facilitar a interação e a navegação.
- **Gráficos Interativos:** Suporta vários tipos de gráficos, incluindo gráficos de pizza, gráficos de barras e muito mais, para representar visualmente os dados.
- **Personalização de Estilo:** Permite personalizar a aparência dos gráficos usando arquivos CSS, para uma experiência visual única.
- **Armazenamento de Dados Eficiente:** Utiliza o padrão Singleton para gerenciar o armazenamento de dados de forma eficiente e centralizada.
- **Exportação de Gráficos:** Oferece a capacidade de exportar gráficos como imagens PNG para compartilhamento ou uso posterior.
- **Fluxo de Perguntas Configurável:** Implementa um fluxo de perguntas configurável para coletar dados específicos do usuário, facilitando a análise e o acompanhamento.

## 🛠 [Arquitetura](#-arquitetura)

O projeto segue uma arquitetura MVC (Model-View-Controller), onde:

- **Model (Modelo):** Representado pela classe `DataStore`, que gerencia o armazenamento e o acesso aos dados da aplicação.
- **View (Visão):** Composta por arquivos FXML que definem a interface do usuário e um arquivo CSS para estilizar os elementos da interface.
- **Controller (Controlador):** Consiste em várias classes de controladores (por exemplo, `HelloController`, `Secondary`, `Third`, `Four` e `Charts`) responsáveis por controlar a lógica de apresentação e interação com o usuário.

## 🚀 [Instalação](#-instalação)

1. Clone este repositório em sua máquina local.
2. Importe o projeto em sua IDE Java preferida.
3. Execute a aplicação a partir da classe `HelloApplication`.

## ☕ [Contribuição](#-contribuição)

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues para relatar problemas ou sugerir novos recursos. Além disso, pull requests são encorajados para propor melhorias no código-fonte.

## 💻 [Telas](#-telas)

Aqui estão algumas capturas de tela da aplicação:

- Tela 1:
  ![Tela1](https://i.imgur.com/BpN0XCZ.png)

- Tela 2:
  ![Tela2](https://i.imgur.com/g0iZzPS.png)

- Tela 3:
  ![Tela3](https://i.imgur.com/uEUZ1yx.png)

- Tela 4:
  ![Tela4](https://i.imgur.com/TPEH3Ps.png)

- Tela 5:
  ![Tela5](https://i.imgur.com/suePSTP.png)

## 📚 [Tecnologias](#-tecnologias)

- **JavaFX:** Plataforma de software para a criação e entrega de experiências de desktop e aplicativos de Internet ricos em recursos.
- **FXML:** Linguagem de marcação declarativa usada para criar interfaces de usuário em JavaFX.
- **CSS:** Folhas de estilo em cascata para estilizar a aparência dos elementos da interface do usuário.

## 🚧 [Status do Projeto](#-status-do-projeto)

O objetivo deste projeto é diagnosticar uma empresa fictícia ou real e analisar suas metas ESG (Meio Ambiente, Social e Governança), fornecendo gráficos para uma compreensão mais clara.

## 📫 [Autores](#-autores)

👤 Giovanne Monti Guedes Morgado - G763289  
👤 Isabela Cicilio de Andrade - G8694J8  
👤 João Pedro Barreto Alves - N417838  
👤 Lucca Gomes Ramos - G820053  
👤 Raphael Della Torre Gimenes - N202HJ4  

## 📹 [Tutorial](#-tutorial)

Para assistir a um tutorial detalhado sobre como utilizar o Gráficos-MetasESG, [clique aqui](https://www.youtube.com/watch?v=3h-xdhCnmxE).
