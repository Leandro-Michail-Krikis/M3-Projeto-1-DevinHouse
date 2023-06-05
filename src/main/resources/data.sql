MERGE INTO PASSAGEIRO (CPF, NOME, DATA_NASCIMENTO, CLASSIFICACAO, MILHAS) VALUES 
('000.000.000-00', 'Rachel Green', '1969-01-11', 'VIP', 100),
('111.111.111-11', 'Phoebe Buffay', '1963-07-30', 'OURO', 100),
('222.222.222-22', 'Ross Geller', '1966-11-02', 'PRATA', 100),
('333.333.333-33', 'Monica Geller', '1964-06-15', 'OURO', 100),
('444.444.444-44', 'Chandler Bing', '1969-08-19', 'OURO', 100),
('555.555.555-55', 'Joey Tribbiani', '1967-07-25', 'BRONZE', 100),
('666.666.666-66', 'Mike Hannigan', '1969-04-06', 'VIP', 100),
('777.777.777-77', 'Gunther Tyler', '1962-05-28', 'ASSOCIADO', 100),
('888.888.888-88', 'Janice Wheeler', '1961-08-07', 'BRONZE', 75),
('999.999.999-99', 'Richard Burke', '1945-01-29', 'BRONZE', 50);

MERGE INTO Assento (assento_id, fileira, poltrona, emergencial) VALUES
('1A', 1, 'A', false), ('1B', 1, 'B', false), ('1C', 1, 'C', false), ('1D', 1, 'D', false), ('1E', 1, 'E', false), ('1F', 1, 'F', false),
('2A', 2, 'A', false), ('2B', 2, 'B', false), ('2C', 2, 'C', false), ('2D', 2, 'D', false), ('2E', 2, 'E', false), ('2F', 2, 'F', false),
('3A', 3, 'A', false), ('3B', 3, 'B', false), ('3C', 3, 'C', false), ('3D', 3, 'D', false), ('3E', 3, 'E', false), ('3F', 3, 'F', false),
('4A', 4, 'A', false), ('4B', 4, 'B', false), ('4C', 4, 'C', false), ('4D', 4, 'D', false), ('4E', 4, 'E', false), ('4F', 4, 'F', false),
('5A', 5, 'A', true), ('5B', 5, 'B', true), ('5C', 5, 'C', true), ('5D', 5, 'D', true), ('5E', 5, 'E', true), ('5F', 5, 'F', true),
('6A', 6, 'A', true), ('6B', 6, 'B', true), ('6C', 6, 'C', true), ('6D', 6, 'D', true), ('6E', 6, 'E', true), ('6F', 6, 'F', true),
('7A', 7, 'A', false), ('7B', 7, 'B', false), ('7C', 7, 'C', false), ('7D', 7, 'D', false), ('7E', 7, 'E', false), ('7F', 7, 'F', false),
('8A', 8, 'A', false), ('8B', 8, 'B', false), ('8C', 8, 'C', false), ('8D', 8, 'D', false), ('8E', 8, 'E', false), ('8F', 8, 'F', false),
('9A', 9, 'A', false), ('9B', 9, 'B', false), ('9C', 9, 'C', false), ('9D', 9, 'D', false), ('9E', 9, 'E', false), ('9F', 9, 'F', false),
('10A', 10, 'A', false), ('10B', 10, 'B', false), ('10C', 10, 'C', false), ('10D', 10, 'D', false), ('10E', 10, 'E', false)