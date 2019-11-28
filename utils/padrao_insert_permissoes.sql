-- Padrão de carga de dados para permissões do sistema
INSERT INTO pdaf.permissao(nome, ativo) 
	VALUES ('VISUALIZAR_'[Entidade], true),
		('INSERIR_'[Entidade], true),
		('ALTERAR_'[Entidade], true),
		('DELETAR_'[Entidade], true); 