*** Settings ***
Library    SeleniumLibrary

Suite Setup    Open Browser    http://localhost:3000/login    chrome
Suite Teardown    Close Browser

*** Variables ***
${USERNAME_INPUT}    xpath=//input[@placeholder="Usuário"]
${PASSWORD_INPUT}    xpath=//input[@placeholder="Senha"]
${SUBMIT_BUTTON}     xpath=//button[contains(., "Entrar")]
${TABELA_FUNCIONARIOS}    xpath=//table

*** Test Cases ***
Login Com Sucesso E Valida Tela De Funcionarios
    [Documentation]    Realiza login válido e verifica que redirecionou para a tela de funcionários (tabela visível).
    Input Text    ${USERNAME_INPUT}    johnjohn
    Input Text    ${PASSWORD_INPUT}    123456
    Click Button  ${SUBMIT_BUTTON}
    Wait Until Element Is Visible    ${TABELA_FUNCIONARIOS}    10s


