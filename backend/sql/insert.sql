INSERT INTO `brands` (`id`, `created_at`, `enable`, `name`) VALUES
(1, '2022-10-12 19:03:39', b'1', 'KIA'),
(2, '2022-10-12 19:03:48', b'1', 'MAZDA'),
(3, '2022-10-12 19:03:55', b'1', 'KIA'),
(4, '2022-10-12 19:03:59', b'1', 'BWM'),
(5, '2023-11-19 23:25:18', b'1', 'BWM'),
(6, '2023-11-23 22:50:53', b'1', 'BWM'),
(7, '2023-11-23 22:50:55', b'1', 'BWM'),
(8, '2023-11-23 22:50:56', b'1', 'BWM'),
(9, '2023-11-23 22:50:57', b'1', 'BWM'),
(10, '2023-11-23 22:50:58', b'1', 'BWM');

--
-- Volcado de datos para la tabla `categories`
--

INSERT INTO `categories` (`id`, `enable`, `group_name`, `name`) VALUES
(1, b'1', 'General', 'MOTOS'),
(2, b'1', 'General', 'CARROS update'),
(20, b'1', 'Otro', 'OTRO ACTUALIZADO');

--
-- Volcado de datos para la tabla `players`
--

INSERT INTO `players` (`id`, `cellphone`, `name`, `photo`, `position`) VALUES
(1, '3122195522', 'Alexander Londono', '', 'Defensa Central'),
(2, '3122195521', 'Alejandro Londoño Espejo', NULL, 'Defensa');

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_MODERATOR'),
(3, 'ROLE_ADMIN');

--
-- Volcado de datos para la tabla `type_doc_identifications`
--

INSERT INTO `type_doc_identifications` (`id`, `abbreviation`, `code`, `created_at`, `enable`, `name`, `updated_at`) VALUES
(1, 'RC', '11', '2022-08-19', b'1', 'Registro civil', '2022-08-19 22:53:57'),
(2, 'TI', '12', '2022-08-19', b'1', 'Tarjeta de identidad', '2022-08-19 22:53:57'),
(3, 'CC', '13', '2022-08-19', b'1', 'Cédula de ciudadanía', '2022-08-19 22:53:57'),
(4, 'TIE', '21', '2022-08-19', b'1', 'Tarjeta de extranjería', '2022-08-19 22:53:57'),
(5, 'CE', '22', '2022-08-19', b'1', 'Cédula de extranjería', '2022-08-19 22:53:57'),
(6, 'NIT', '31', '2022-08-19', b'1', 'NIT', '2022-08-19 22:53:57'),
(7, 'PP', '41', '2022-08-19', b'1', 'Pasaporte', '2022-08-19 22:53:57'),
(8, 'Identificación extranjero', '42', '2022-08-19', b'1', 'Documento de identificación extranjero', '2022-08-19 22:53:57'),
(9, 'NIT de otro país', '50', '2022-08-19', b'1', 'NIT de otro país', '2022-08-19 22:53:57'),
(10, 'NUIP', '91', '2022-08-19', b'1', 'NUIP *', '2022-08-19 22:53:57');

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `email`, `enable`, `last_name`, `name`, `password`, `username`) VALUES
(1, 'alexlondon07@gmail.com', b'1', 'Londono', 'Alex', '$2a$10$ciTLssO91kyZSC6MJTkxWON.Zj8.YqTDGXauNoxxY2MU0h0yK21bG', 'alexlondon07'),
(4, 'emiliana@emi.com', b'1', 'Londono', 'emiliana', '$2a$10$6VEFC7fnyupp3KTjg8Cuc.5gWN6B6itfHzPCbiWTNzUbiPsGnC.1C', 'emiliana');

--
-- Volcado de datos para la tabla `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(4, 1),
(4, 2),
(4, 3);
COMMIT;


