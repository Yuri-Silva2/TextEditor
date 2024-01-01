# Text Editor Application

## Visão Geral
O Aplicativo é um editor de texto baseado em JavaFX que permite aos usuários criar, editar e salvar arquivos de texto usando uma interface com abas.

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
javac -cp "caminho/para/javafx-sdk-<versão>/lib/*" -d out src/org/texteditor/*.java src/org/texteditor/controllers/*.java src/org/texteditor/models/*.java src/org/texteditor/viewers/menu/*.java src/org/texteditor/viewers/pane/*.java
java --module-path "caminho/para/javafx-sdk-<versão>/lib/" --add-modules javafx.controls,javafx.fxml -cp out org.texteditor.TextEditorApplication
```

## Uso

- Inicie o aplicativo e comece a criar/editar seus arquivos de texto.
- Use a interface com abas para gerenciar vários arquivos.
- Salve seu trabalho usando as opções do menu "Arquivo".


