*** Settings ***
Library           SeleniumLibrary

Suite Setup       Open Browser    about:blank    chrome
Suite Teardown    Close Browser

*** Variables ***
${BASE_URL}                   http://localhost:3000
${TOKEN}                      eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huam9obiIsImlhdCI6MTc0OTg2NjI1NywiZXhwIjoxNzQ5OTUyNjU3fQ.V1cIn8WSXQH-105Sc9A9mW4dEkZ-y63PtzMaDjs_P8o
${TABELA_FUNCIONARIOS_URL}    ${BASE_URL}/gestao-funcionarios

${TABLE_XPATH}     xpath=//table
${ROW_XPATH}       xpath=//table/tbody/tr
${HEADER_NOME}     xpath=//table/thead/tr/th[1]
${BTN_ALTERAR}     xpath=//button[contains(., "Alterar")]
${BTN_INATIVAR}    xpath=//button[contains(., "Inativar")]

*** Test Cases ***
Abrir Tabela De Funcionarios Com Token
    [Documentation]    Acessa a página de funcionários injetando token no localStorage para simular login.
    Go To    ${TABELA_FUNCIONARIOS_URL}
    Execute Javascript    window.localStorage.setItem("jwt_token", "${TOKEN}");
    Reload Page
    Wait Until Page Contains Element    ${TABLE_XPATH}    10s
    Page Should Contain Element         ${HEADER_NOME}
    Page Should Contain Element         ${BTN_ALTERAR}
    Page Should Contain Element         ${BTN_INATIVAR}

Verificar Linhas Na Tabela Funcionarios
    [Documentation]    Verifica se há ao menos uma linha na tabela de funcionários.
    Go To    ${TABELA_FUNCIONARIOS_URL}
    Execute Javascript    window.localStorage.setItem("jwt_token", "${TOKEN}");
    Reload Page
    Wait Until Page Contains Element    ${ROW_XPATH}    10s
    ${num_linhas}=    Get Element Count    ${ROW_XPATH}
    Should Be True    ${num_linhas} > 0    A tabela deve conter pelo menos um funcionário.

Verificar Botao Inativar Desabilitado Para Funcionarios Inativos
    [Documentation]    Verifica se o botão "Inativar" está desabilitado para funcionários já inativos.
    Go To    ${TABELA_FUNCIONARIOS_URL}
    Execute Javascript    window.localStorage.setItem("jwt_token", "${TOKEN}");
    Reload Page
    Wait Until Page Contains Element    ${ROW_XPATH}    10s
    ${rows}=    Get WebElements    ${ROW_XPATH}
    FOR    ${row}    IN    @{rows}
        ${cell}=    Get WebElement    xpath=.//td[4]    element=${row}
        ${status}=  Execute Javascript    return arguments[0].textContent.trim();    ${cell}
        Run Keyword If    '${status}' == 'Inativo'    Verificar Botao Inativar Disabled    ${row}
    END

*** Keywords ***
Verificar Botao Inativar Disabled
    [Arguments]    ${row_element}
    ${btn}=    Get WebElement    xpath=.//button[contains(., "Já inativo")]    element=${row_element}
    Element Should Be Disabled    ${btn}
