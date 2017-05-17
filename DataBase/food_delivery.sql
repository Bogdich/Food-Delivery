CREATE DATABASE  IF NOT EXISTS `food_delivery`
USE `food_delivery`;

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `image_URL` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

LOCK TABLES `category` WRITE;
INSERT INTO `category` VALUES (1,'Пицца','https://home-pizza.com/media/_versions_/pizza_11.06.16/pizza_karbovenskaya_catalog_product_detail.jpg'),(2,'Суши','https://home-sushi.ru/media/_versions_/new_18.02.16/img_5133_catalog_product_detail.jpg'),(3,'Лапша','https://home-sushi.ru/media/_versions_/wok/lapsha_govyad_catalog_product_detail.jpg'),(4,'Салаты и супы','https://home-pizza.com/media/_versions_/pizza_11.06.16/salad_s_telyatinoy_catalog_product_detail.jpg'),(5,'Пироги','https://home-pizza.com/media/_versions_/pirogi_05/pirog_yagodnii_catalog_product_detail.jpg'),(6,'Закуски','https://home-pizza.com/media/_versions_/buff_catalog_product_detail.jpg'),(7,'Десерты','https://home-pizza.com/media/_versions_/tiramisu_catalog_product_detail.jpg'),(8,'Напитки','https://home-pizza.com/media/_versions_/pizza_11.06.16/mors_catalog_product_detail.jpg');
UNLOCK TABLES;

DROP TABLE IF EXISTS `dish`;
CREATE TABLE `dish` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(250) NOT NULL,
  `weight` int(11) NOT NULL,
  `price` decimal(20,0) NOT NULL,
  `category_id` int(11) NOT NULL,
  `image_URL` varchar(250) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_dish_category1_idx` (`category_id`),
  CONSTRAINT `fk_dish_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

LOCK TABLES `dish` WRITE;
INSERT INTO `dish` VALUES (1,'Мастер и Маргарита','самая простая пицца',344,6,1,'https://home-pizza.com/media/_versions_/pizza_11.06.16/pizza_master&margarita_catalog_product_detail.jpg'),(2,'Двойная Пепперони','Пицца с кусочками ананаса',344,7,1,'https://home-pizza.com/media/_versions_/%D0%B4%D0%B0%D0%B1%D0%BB_%D0%BF%D0%B5%D0%BF%D0%BF%D0%B5%D1%80%D0%BE%D0%BD%D0%B8_catalog_product_detail.jpg'),(3,'Дары моря','С морепродуктами',344,12,1,'https://home-pizza.com/media/_versions_/%D0%B4%D0%B0%D1%80%D1%8B_%D0%BC%D0%BE%D1%80%D1%8F_catalog_product_detail.jpg'),(4,'4 сыра','4 разных вкуса сыра',344,12,1,'https://home-pizza.com/media/_versions_/pizza_05.2016/pizza_4_chees_catalog_product_detail.jpg'),(5,'Чикаго','Куриная грудка, болгарский перец, кукуруза в зернах, маринованный огурчик, лук красный. Сыр Моцарелла и соус томатный. Украшена фирменным соусом.',344,12,1,'https://home-pizza.com/media/_versions_/%D1%87%D0%B8%D0%BA%D0%B0%D0%B3%D0%BE_catalog_product_detail.jpg'),(6,'Грибная','Простая грибная',500,15,1,'https://home-pizza.com/media/_versions_/pizza_05.2016/pizza_gribnaya_catalog_product_detail.jpg'),(29,'Сет Мечта','Ролл Филадельфия Гуд, ролл Уик-енд у Берни, ролл сливочный с овощами, ролл с огурцом, ролл Крабби Уильямс.',920,20,2,'https://home-sushi.ru/media/_versions_/24.03.2016/set_vesennii_catalog_product_detail.jpg'),(30,'Сет На каждый день','Филадельфия Гуд, Цезарь ролл, ролл классический, ролл простой с огурцом, ролл курица на снегу.',870,19,2,'https://home-sushi.ru/media/_versions_/new_18.02.16/img_5155_catalog_product_detail.jpg'),(31,'Сет Вечерний','Ролл Горячая тортилья с курицей и халапеньо, ролл Клаус, ролл Филадельфия Гуд, ролл сливочный с огурцом, суши с омлетом 2 шт.',795,17,2,'https://home-sushi.ru/media/_versions_/%D1%81%D0%B5%D1%82_%D0%B2%D0%B5%D1%87%D0%B5%D1%80%D0%BD%D0%B8%D0%B9_mini_catalog_product_detail.jpg'),(32,'Сет Романтика','Ролл Горячий бекон, Ролл Филадельфия 3/4, Ролл Уик-энд у Берни, Ролл Классический, Ролл Икрим, Ролл Фестивальный',1200,28,2,'https://home-sushi.ru/media/_versions_/%D1%80%D0%BE%D0%BC%D0%B0%D0%BD%D1%82%D0%B8%D0%BA%D0%B0_catalog_product_detail.jpg'),(33,'Сет Фейерверк','Ролл Курица на снегу, ролл Филадельфия \"Сан-Франциско\", ролл Цезарь, ролл Классический, ролл Уикенд у Берни, ролл Звезда.',1415,30,2,'https://home-sushi.ru/media/_versions_/%D1%84%D0%B5%D0%B9%D0%B5%D1%80%D0%B2%D0%B5%D1%80%D0%BA_catalog_product_detail.jpg'),(34,'Инари сет','Инари с курицей, Инари с сурими краба, Инари с угрем, Инари с креветкой, Инари с лососем.',400,15,2,'https://home-sushi.ru/media/_versions_/img_3941_catalog_product_detail.jpg'),(35,'Лапша с курицей','Лапша Удон, морковь, перец болгарский, капуста Пекинская, куриное филе ломтики, кукуруза мини початки, зеленый лук.',300,10,3,'https://home-sushi.ru/media/_versions_/wok/lapsha_kura_catalog_product_detail.jpg'),(36,'Лапша с овощами','Лапша Удон, морковь, перец болгарский, капуста Пекинская, шампиньоны, кукуруза мини початки, ананас консервированный, зеленый лук.',250,9,3,'https://home-sushi.ru/media/_versions_/wok/lapsha_ovosch_catalog_product_detail.jpg'),(37,'Лапша с креветками и кальмарами','Лапша Рамен, креветки, кальмар, морковь, перец болгарский, капуста Пекинская, ананас консервированный , яйцо куриное, чеснок, зеленый лук.',350,13,3,'https://home-sushi.ru/media/_versions_/wok/lapsha_more_catalog_product_detail.jpg'),(38,'Суп куриный с лапшой','Куриный суп с лапшой и перепелиным яйцом, картофелем и гренками.',250,6,4,'https://home-sushi.ru/media/_versions_/pizza_11.06.16/sup_kur_s_lap_catalog_product_detail.jpg'),(39,'Суп рамен со свининой','Свинина, лапша Рамен, мини кукуруза и морковь.',300,6,4,'https://home-sushi.ru/media/_versions_/sup/sup_ramen_catalog_product_detail.jpg'),(40,'Суп-крем из шампиньонов с гренками','Шампиньоны, гренки.',270,6,4,'https://home-sushi.ru/media/_versions_/pizza_11.06.16/sup_shamp_catalog_product_detail.jpg'),(41,'Салат Греческий','Листья салата, бальзамическая заправка, огурцы, томаты, перец болгарский, маслины, сыр Фета, лук красный, бальзамический крем соус.',170,7,4,'https://home-sushi.ru/media/_versions_/pizza_11.06.16/salad_greek_catalog_product_detail.jpg'),(42,'Салат Цезарь с курицей','Жареное куриное филе, гренки, томаты черри, перепелиные яйца, листья салата, соус цезарь, пармезан.',220,7,4,'https://home-sushi.ru/media/_versions_/pizza_11.06.16/salad_cesar_catalog_product_detail.jpg'),(43,'Салат крабовый с икрой','Салат коктейль с сурими краба, Чукой, огурцами, томатами, салатом, икрой черной сурими и тобико.',200,7,4,'https://home-sushi.ru/media/_versions_/salad/salad_krab_catalog_product_detail.jpg'),(44,'Пирог с зеленым луком, рисом и яйцом','Зеленый лук, рис и яйцо.',900,14,5,'https://home-pizza.com/media/_versions_/pirogi_05/pirog_s_lukom_i_yaitsom_catalog_product_detail.jpg'),(45,'Пирог с курицей, грибами и овощами','Копченая курица, шампиньоны, перец болгарский, лук зеленый и красный.',900,15,5,'https://home-pizza.com/media/_versions_/pirogi_05/pirog_s_kkur_grib_i_ov_catalog_product_detail.jpg'),(46,'Пирог с мясом и картофелем','Бекон, фарш, картофель, зеленый лук.',900,15,5,'https://home-pizza.com/media/_versions_/pirogi_05/pirog_s_myasom_i_kart_catalog_product_detail.jpg'),(47,'Пирог ягодный','Ягодное ассорти, творог, джем.',900,13,5,'https://home-pizza.com/media/_versions_/pirogi_05/pirog_yagodnii_catalog_product_detail.jpg'),(48,'Гренки чесночные','Жареные бородинские гренки с соусами Сметанно-чесночный и кетчуп.',200,4,6,'https://home-pizza.com/media/_versions_/grenki_catalog_product_detail.jpg'),(49,'Куриные нагетсы','Жареные кусочки цыпленка в панировке с соусом Флоренция.',160,4,6,'https://home-pizza.com/media/_versions_/naggets_catalog_product_detail.jpg'),(50,'Крылышки Баффало','Куриные крылышки с соусами Флоренция и кетчуп.',230,5,6,'https://home-pizza.com/media/_versions_/buff_catalog_product_detail.jpg'),(51,'Торт Тирамису','Знаменитый во всем мире вкус, прекрасно сочетающийся с кофе. Торт представляет собой нежные бисквиты, прослоенные сливочной массой со вкусом кофе и амаретто.',115,4,7,'https://home-pizza.com/media/_versions_/tiramisu_catalog_product_detail.jpg'),(52,'Торт Чизкейк классический','Начинка из сливочного сыра на рассыпчатом песочном корже - на сегодняшний день это самый востребованный вкус благодаря своей простоте и незамысловатости.',100,4,7,'https://home-pizza.com/media/_versions_/cheez_classic_catalog_product_detail.jpg'),(53,'Торт Чизкейк Шоколадный','Натуральный горький шоколад, смешанный с нежным крем-чизом удачно сочетается с хрустящим песочным коржом.',100,3,7,'https://home-pizza.com/media/_versions_/cheez_choco_catalog_product_detail.jpg'),(54,'Морс Клюквенный','Вкуснейший морс из клюквы',1,2,8,'https://home-pizza.com/media/_versions_/pizza_11.06.16/mors_catalog_product_detail.jpg'),(55,'Сок (в ассортименте)','Апельсиновый, яблочный,виноградный',1,2,8,'https://home-pizza.com/media/_versions_/drinks/rich_catalog_product_detail.jpg'),(56,'Спрайт',' ',1,2,8,'https://home-pizza.com/media/_versions_/pizza_11.06.16/sprite_catalog_product_detail.jpg'),(57,'Кола',' ',1,2,8,'https://home-pizza.com/media/_versions_/pizza_11.06.16/coca-cola_catalog_product_detail.jpg'),(58,'Фанта',' ',1,2,8,'https://home-pizza.com/media/_versions_/pizza_11.06.16/fanta_catalog_product_detail.jpg');
UNLOCK TABLES;


DROP TABLE IF EXISTS `dish_has_order`;

CREATE TABLE `dish_has_order` (
  `dish_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `amount` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY (`dish_id`,`order_id`),
  KEY `fk_dish_has_order_order1_idx` (`order_id`),
  KEY `fk_dish_has_order_dish1_idx` (`dish_id`),
  CONSTRAINT `fk_dish_has_order_dish1` FOREIGN KEY (`dish_id`) REFERENCES `dish` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_dish_has_order_order1` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `dish_has_order` WRITE;
INSERT INTO `dish_has_order` VALUES (1,1,1),(3,1,1),(5,5,1),(30,5,2),(35,2,1),(35,3,1),(35,4,1),(37,2,1),(37,3,1),(37,4,1);
UNLOCK TABLES;

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_user1_idx` (`user_id`),
  CONSTRAINT `fk_order_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

LOCK TABLES `order` WRITE;
INSERT INTO `order` VALUES (1,'2017-05-12 08:17:57',1),(2,'2017-05-12 08:23:47',1),(3,'2017-05-12 08:25:11',1),(4,'2017-05-12 08:26:01',1),(5,'2017-05-12 08:51:02',1);
UNLOCK TABLES;

DROP TABLE IF EXISTS `subscription`;
CREATE TABLE `subscription` (
  `category_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`category_id`,`user_id`),
  KEY `fk_category_has_user_user1_idx` (`user_id`),
  KEY `fk_category_has_user_category1_idx` (`category_id`),
  CONSTRAINT `fk_category_has_user_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_category_has_user_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `subscription` WRITE;
UNLOCK TABLES;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `is_admin` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES (1,'admin','admin',1),(2,'client','1234',0),(37,'Ikloo','12345',NULL),(38,'ÐÐºÐ»Ñ','12345',NULL),(39,'%D0%9A%D0%B8%D1%80%D1%8F','123fdsfds',NULL),(40,'Киря','123fdsfds',NULL);
UNLOCK TABLES;

DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `number` varchar(45) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `fk_user_info_user_idx` (`user_id`),
  CONSTRAINT `fk_user_info_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user_info` WRITE;
INSERT INTO `user_info` VALUES ('ÐÐ¸ÑÐ¸Ð»Ð»','ÐÑÐ´ÐµÐ²Ð¸Ñ','+375445777510','Ikloo@yandex.ru','ÐÐ¸Ð½ÑÐº ÐÐ°ÑÑÑÐµÐ²Ð¸ÑÐ° 56-101',37),('ÐÐ¸ÑÑ','ÐÐ¸ÑÑ','+2134124','Ikloo','Fdsfsd Sdfsdf Fsdfsd',38),('Kirya','Букря','+3124124','Ikll','Матус 1231 Fdsfsd',40);
UNLOCK TABLES;
