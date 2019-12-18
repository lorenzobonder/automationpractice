# automationpractice

# Overview
Este código foi feito no IntelliJ IDEA com o Java 8, caso contrário a execução dos scripts não funcionará. Foram implementadas, usando PageObjects, packages das páginas, suportes (para evidências e outras coisas de suporte) e a de execução dos testes.

O arquivo pom.xml já possui todos as bibliotecas que foram usadas como dependência para download via Maven. Se for necessário configurar algo, somente adicionar ao pom.

O framework usado para montar as páginas e manipulá-las foi o Selenium WebDriver e para teste usou-se o JUnit, essas bibliotecas estão inclusas dentro do pom para download com as versões estáveis mais recentes, caso não possua as versões mais recentes de ambos. E todo o código foi usando o Java como linguagem de programação, devido a maior familiaridade a essa linguagem de código que outros.

# Configurações
O código não usou algum programa de testes na nuvem como o BrowserStack, tudo foi feito localmente, inclusive a geração de evidências. Será necessário criar uma pasta local no seu computador (C:\Users\Lorenzo\automation_output\automationPractice\automationPractice, onde o segundo automationPractice é referente ao nome do arquivo de evidência: automationPractice20191218174422.png) e adicionar o caminho da pasta no método TestBuyClothing, na classe BuyClothingTest (package tests), trocando o caminho existente dentro do código.

A aplicação foi 100% feita em cima do browser Google Chrome, versão 79. Para funcionar, será necessário fazer o download do chromeDriver mais recente, que pode ser obtido no link a seguir: https://chromedriver.chromium.org/downloads e após feito o download, deve-se mover o executável/binário do chromeDriver para a pasta onde o .bin/.exe mais antigo exista (C:\Users\Lorenzo\drivers, por exemplo), substituindo o antigo pelo novo, caso contrário não será possível rodar nenhum caso de teste dentro do IntelliJ. Além deve-se configurar no método createChromeInstance() na classe Web (package support), trocando dentro da chamada System.setProperty o diretório de onde o seu chromeDriver está localizado.

# Execução dos Testes
O teste pedido em si deve ser executado via IntelliJ. Todos os testes possuem sua prórpia classe e três métodos principais: 
 - setUp() -> inicializa a instância do browser e abre a página principal.
 - nomeDoTeste() -> executa o caso de teste em si.
 - shutdown() -> fecha a instância do Google Chrome quando finalizada a execução.

Novamente, importante frisar novamente que as evidências são salvas localmente, portanto deve-se trocar o diretório de saída para geração das evidências.

O IntelliJ intuitivamente oferece ao usuário a rodar cada test case individualmente, "acessando" o nome do método, ou todos test cases de uma vez só, se deixar o cursor em foco sobre o nome da classe em si.
