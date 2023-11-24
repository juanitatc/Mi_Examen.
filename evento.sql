SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `evento` (
  `titulo` varchar(50) NOT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `ubicacion` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO `evento` (`titulo`, `descripcion`, `fecha`, `ubicacion`) VALUES
('Conferencia de Desarrollo Web', 'Una conferencia sobre las últimas tendencias en desarrollo web.', '2023-12-15', 'Centro de Convenciones XYZ'),
('Feria Tecnológica 2024', 'Exhibición de nuevas tecnologías y productos innovadores.', '2024-04-02', 'Recinto Ferial ABC'),
('Taller de Inteligencia Artificial', 'Aprende a implementar soluciones de inteligencia artificial en la práctica.', '2023-11-28', 'Laboratorio de Innovación'),
('Hackathon de Desarrollo Sostenible', 'Competencia de programación para encontrar soluciones a problemas ambientales.', '2024-02-10', 'Espacio Coworking Verde'),
('Seminario de Ciberseguridad', 'Explorando las últimas amenazas y estrategias de ciberseguridad.', '2023-12-08', 'Auditorio Seguro'),
('Curso de Diseño UX/UI', 'Aprende principios y prácticas de diseño de experiencia de usuario.', '2024-01-20', 'Escuela de Diseño Creativo'),
('Encuentro de Emprendedores', 'Networking y charlas inspiradoras para emprendedores.', '2023-11-30', 'Centro de Emprendimiento Innovador'),
('Charla sobre Blockchain', 'Explorando el impacto de la tecnología blockchain en diversas industrias.', '2024-03-05', 'Sala de Conferencias TechHub'),
('Presentación de Nuevos Productos', 'Evento de lanzamiento de productos innovadores en el mercado.', '2023-12-05', 'Salón de Eventos Corporativos'),
('Encuentro Cultural Multidisciplinario', 'Celebración de la diversidad a través de expresiones artísticas.', '2024-02-22', 'Teatro Municipal');

ALTER TABLE `evento`
  ADD PRIMARY KEY (`titulo`);
COMMIT;

