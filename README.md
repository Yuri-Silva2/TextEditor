# Text Editor Application

## Visão Geral

O Aplicativo é um editor de texto baseado em JavaFX que permite aos usuários criar, editar e salvar arquivos de texto
usando uma interface com abas.

## Recursos

- Criar, abrir e salvar arquivos de texto
- Interface com abas para trabalhar com vários arquivos simultaneamente
- Alerta para salvar alterações não salvas ao fechar uma aba
- Interface responsiva e amigável ao usuário

## Futuros novos recursos

- Salvamento automático das guias já salvas a cada X segundos
- Arquivos não salvos considerados temporários em memória para possível reabertura
- Teclas de atalho
- Layout personalizável

## Como Começar

### Pré-requisitos

- Kit de Desenvolvimento Java (JDK) instalado
- Biblioteca JavaFX
- Apache Commons Lang ( StringUtils )

### Instalação

1. Clone o repositório:

```bash
git clone https://github.com/Yuri-Silva2/TextEditor.git
```

2. Navegue até o diretório do projeto:

```bash
cd text-editor
```

3. Compile e execute o aplicativo:

```bash
javac -cp "dependencies\*;dependencies\javafx\lib\*" -d out src\main\java\org\texteditor\*.java src\main\java\org\texteditor\controllers\*.java src\main\java\org\texteditor\model\*.java src\main\java\org\texteditor\viewers\menu\*.java src\main\java\org\texteditor\viewers\pane\*.java src\main\java\org\texteditor\viewers\tab\*.java src\main\java\org\texteditor\viewers\tab\find\*.java

xcopy /s /i /y src\main\resources\* out\

java --module-path "dependencies\;dependencies\javafx\lib\" --add-modules javafx.controls -cp out org.texteditor.Main
```

## Uso

- Inicie o aplicativo e comece a criar/editar seus arquivos de texto.
- Use a interface com abas para gerenciar vários arquivos.
- Salve seu trabalho usando as opções do menu "Arquivo".


