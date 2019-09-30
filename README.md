# Sales Import
	Projeto de importação de informações de vendas.
	
## Informações importantes
	Pelo exemplo não foi implementado cpf e cnpj validator.
	Caso haja erro em uma linha ela é ignora e o restante é processado normalmente.
	O processamento dos arquivo é realizado em intervalos de 10 segundos.
	
### Configurações
	"files-folder.in": Folder onde os arquivos são lidos.
	"files-folder.out": Folder onde os arquivos processados são gravados.
	"project.not-validate-processed-files": Opção para não validar se os arquivos foram processados anteriormente.
	
### Futuras melhorias
	Labels em arquivo de properties.
	Implementação de testes unitários.