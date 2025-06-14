***Settings***
Library    SeleniumLibrary    # Importa a biblioteca para interação com o navegador

***Keywords***
Abrir Navegador Na Pagina De Login
    Open Browser    http://localhost:3000/login    chrome    # Altere a URL se for diferente
    Maximize Browser Window
    Wait Until Page Contains Element    class:auth-form    timeout=10s    # Espera o formulário carregar

Fechar Navegador
    Close Browser

Preencher Credenciais De Login
    [Arguments]    ${username}    ${password}
    Input Text    xpath://input[@placeholder='Usuário']    ${username}    # Input de Usuário
    Input Password    xpath://input[@placeholder='Senha']    ${password}    # Input de Senha

Clicar No Botao Entrar
    Click Element    class:auth-button    # Botão de Entrar
    Wait Until Element Is Not Visible    class:auth-button[disabled]    timeout=10s    # Espera o botão sair do estado de 'loading'