/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80034
 Source Host           : localhost:3306
 Source Schema         : shootdb

 Target Server Type    : MySQL
 Target Server Version : 80034
 File Encoding         : 65001

 Date: 02/03/2024 15:47:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for equipment_tb
-- ----------------------------
DROP TABLE IF EXISTS `equipment_tb`;
CREATE TABLE `equipment_tb`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '器材id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '器材名',
  `inputdate` datetime NULL DEFAULT NULL COMMENT '添加时间',
  `outputdate` datetime NULL DEFAULT NULL COMMENT '借出时间',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态',
  `context` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '器材介绍',
  `shutternumber` int NULL DEFAULT NULL COMMENT '快门数',
  `imgurl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片路径',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '相机类型',
  `sensor` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '传感器类型',
  `user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '责任人/借出人',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '器材价值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of equipment_tb
-- ----------------------------
INSERT INTO `equipment_tb` VALUES (1, '松下相机', '2024-02-28 18:37:24', '2023-11-02 22:50:46', '维修', '经典品牌相机', 123, 'http://localhost:8080/image/c1.png', '其他', '全画幅', '邱三', 0.00);
INSERT INTO `equipment_tb` VALUES (2, '索尼（SONY）ZV-E10L', '2024-02-15 15:30:03', '2024-03-02 14:39:17', '借出', '轻巧便携：适合日常携带和拍摄，便于记录生活中的精彩时刻。\r\n\r\n20.1MP APS-C 传感器：拥有高像素传感器，可捕捉清晰细腻的影像细节。\r\n\r\n4K 视频录制：支持4K视频录制，可拍摄高质量的视频内容。\r\n\r\n方便自拍功能：配备了便于自拍的折叠式触摸屏，方便自拍和Vlog拍摄。', 567, 'http://localhost:8080/image/c1.png', '其他', '全画幅', '邱三', 0.00);
INSERT INTO `equipment_tb` VALUES (18, '索尼a7大炮', '2018-07-28 00:00:00', '2024-02-26 14:49:20', '借出', '索尼天天降价', 2, 'http://localhost:8080/image/c1.png', '其他', '全画幅', '杰哥', 0.00);
INSERT INTO `equipment_tb` VALUES (19, '索尼a7大炮', '2018-07-28 00:00:00', '2023-11-28 00:00:00', '借出', '索尼天天降价', 213, 'http://localhost:8080/image/c1.png', '其他', '全画幅', '杰哥', 23423.00);
INSERT INTO `equipment_tb` VALUES (20, '索尼a7大炮', '2024-02-28 18:37:31', '2023-11-28 00:00:00', '维修', '索尼天天降价', 213, 'http://localhost:8080/image/c1.png', '其他', '全画幅', '邱三', 0.00);
INSERT INTO `equipment_tb` VALUES (21, '三星打猎相机', '2024-01-27 18:47:15', NULL, NULL, NULL, 23, 'http://localhost:8080/image/c1.png', '其他', '全画幅', '杰哥', 3244.00);
INSERT INTO `equipment_tb` VALUES (22, '尼康相机', '2024-01-27 18:49:08', NULL, NULL, NULL, 123, 'http://localhost:8080/image/c1.png', NULL, '全画幅', '杰哥', 43234.00);
INSERT INTO `equipment_tb` VALUES (23, 'A7000', '2024-01-27 18:50:18', NULL, NULL, '快乐大炮', 23123, 'http://localhost:8080/image/20240127184906banner.png', NULL, '全画幅', '杰哥', 3242.00);
INSERT INTO `equipment_tb` VALUES (24, 'asd', '2024-02-26 16:52:55', NULL, NULL, 'sad', NULL, 'http://localhost:8080/image/20240226165252爱思壁纸_681112.jpg', '设备配件', NULL, NULL, 0.00);
INSERT INTO `equipment_tb` VALUES (25, 'asd', '2024-02-26 16:54:01', NULL, NULL, 'sad', NULL, 'http://localhost:8080/image/20240226165252爱思壁纸_681112.jpg', '设备配件', NULL, NULL, 0.00);

-- ----------------------------
-- Table structure for events_tb
-- ----------------------------
DROP TABLE IF EXISTS `events_tb`;
CREATE TABLE `events_tb`  (
  `id` int NOT NULL COMMENT '活动id',
  `eventname` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动名称',
  `startdate` datetime NULL DEFAULT NULL COMMENT '活动起始日期',
  `enddate` datetime NULL DEFAULT NULL COMMENT '活动结束日期',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动内容',
  `location` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动地点',
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动发起人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of events_tb
-- ----------------------------
INSERT INTO `events_tb` VALUES (1, '晨光微距摄影探索', '2003-04-02 08:00:00', '2004-08-24 08:00:00', '在清晨的微风中，探索微距摄影之美，捕捉小世界的精彩细节与生机。', '花园/郊外小径/山坡', '彭云熙');
INSERT INTO `events_tb` VALUES (2, '水下摄影奇妙之旅', '2014-05-01 08:00:00', '2021-06-09 00:00:00', '穿戴水下摄影装备，沉入水底世界，捕捉水下生物与光影交织出的奇妙画面。', '游泳池/海洋保护区', '莫岚');
INSERT INTO `events_tb` VALUES (3, '历史建筑文化摄影之旅：', '2023-10-23 08:00:00', '2017-01-21 08:00:00', '漫步历史悠久的城市街道，捕捉古老建筑的韵味与文化底蕴。', '博物馆/古镇/文化遗产区', '廖晓明');
INSERT INTO `events_tb` VALUES (4, '舞蹈表演实况摄影', '2007-09-02 08:00:00', '2023-06-20 08:00:00', '记录舞台上舞者优美的动作，捕捉舞蹈表演带来的激情与艺术表达。', '剧院/舞蹈工作室', '汤岚');
INSERT INTO `events_tb` VALUES (5, '极限运动摄影挑战', '2023-06-18 08:00:00', '2019-05-19 08:00:00', '追随极限运动爱好者，捕捉他们在高空、深水等极限环境中的刺激瞬间。', '攀岩场/极限运动基地', '罗嘉伦');
INSERT INTO `events_tb` VALUES (6, '时尚婚纱摄影创意体验', '2013-08-27 08:00:00', '2015-06-04 08:00:00', '打造独特的婚纱摄影作品，展现时尚新娘的个性魅力与幸福时刻。', '精品酒店/花园别墅', '丁嘉伦');

-- ----------------------------
-- Table structure for notice_tb
-- ----------------------------
DROP TABLE IF EXISTS `notice_tb`;
CREATE TABLE `notice_tb`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '公告id',
  `editdate` datetime NULL DEFAULT NULL COMMENT '编辑时间',
  `context` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公告正文',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公告标题',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1003 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice_tb
-- ----------------------------
INSERT INTO `notice_tb` VALUES (2, '2024-03-01 21:20:44', '为展现赵制药有限责任公司的企业文化和团队精神，我们计划进行一次宣传片拍摄活动。以下是相关内容和安排：\n\n拍摄主题：《奋斗 创新 共赢》—— 赵制药的使命与愿景', '赵制药有限责任公司');
INSERT INTO `notice_tb` VALUES (3, '2024-03-01 21:22:18', '为展现企业风采，提升品牌形象，特定于中午12:00在公司总部进行团体照拍摄。请全体员工准时参加，穿着整洁得体。让我们携手合作，共创辉煌！感谢您的配合与支持！', '杰宏贸易有限责任公司');
INSERT INTO `notice_tb` VALUES (4, '2024-03-01 21:22:45', '嗨，摄影爱好者们！我们正筹备一部充满奇幻与想象力的短片《幻境之旅》，现诚邀各位加入我们的拍摄团队。我们需要有经验的摄像师、灯光师、化妆师等等。这将是一次探索创意与艺术的奇妙之旅，我们期待与您一起创造出令人惊叹的视觉效果！', '《幻境之旅》拍摄公告');
INSERT INTO `notice_tb` VALUES (5, '2024-03-01 21:22:59', '各位时尚达人们，我们即将为一本知名时尚杂志进行拍摄工作，展示最新的时尚潮流与风格。我们需要一批具有时尚审美和摄影技巧的团队成员，包括摄影师、化妆师、模特等。如果您对时尚充满热情，并渴望展示自己的创意，请加入我们的行列，一同留下精彩的时尚印记！', '时尚杂志拍摄公告');
INSERT INTO `notice_tb` VALUES (6, '2024-03-01 21:23:42', '尊敬的合作伙伴们，我们即将开展一场商业广告拍摄活动，旨在为您的品牌塑造独特而引人注目的形象。我们需要一支专业的拍摄团队，包括摄影师、导演、剪辑师等。如果您有丰富的经验和创意，并对广告拍摄感兴趣，请尽快与我们联系，让我们一同打造出令人难忘的广告作品！', '商业广告拍摄公告');
INSERT INTO `notice_tb` VALUES (7, '2024-03-01 21:25:49', '喜欢户外风光摄影的摄影爱好者们，我们将举办一场户外风光摄影工作坊！由资深摄影师亲自指导，学习拍摄技巧和分享经验。工作坊时间为下周日全天，地点在山区风景区。名额有限，抓紧报名！', '户外风光摄影工作坊');
INSERT INTO `notice_tb` VALUES (8, '2024-03-01 21:26:03', '摄影艺术家们，我们邀请您参与艺术人像摄影展征集！展示您的创意和才华，与观众分享独特的人像作品。作品征集截止日期为下月初，请将作品发送至展览邮箱（展览邮箱地址）。展览时间和地点将另行通知。', '艺术人像摄影展征集');
INSERT INTO `notice_tb` VALUES (1001, '2024-02-26 15:43:16', '我们诚挚邀请您参与电影《追逐光影》的拍摄工作！该片讲述了一个年轻摄影师在追寻梦想的道路上所经历的成长和困惑。我们需要一支具有创意和才华的团队，共同打造这部感人至深的作品。如果您热爱摄影，愿意用镜头记录生活的点滴，欢迎加入我们的大家庭！', '《追逐光影》拍摄公告');

-- ----------------------------
-- Table structure for project_tb
-- ----------------------------
DROP TABLE IF EXISTS `project_tb`;
CREATE TABLE `project_tb`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '项目id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '项目名',
  `setdate` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `contont` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '项目内容',
  `staff_list` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '项目人员',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态',
  `manager` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '项目负责人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 102 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project_tb
-- ----------------------------
INSERT INTO `project_tb` VALUES (101, '夏日时光', '2024-02-14 20:57:09', '包括以下内容：\r\n\r\n沙滩日出日落：在海滩上拍摄早晨和傍晚的日出日落景色，捕捉夏日海滩的宁静和美丽。\r\n\r\n游泳池畅游：记录人们在游泳池中尽情畅游、嬉戏的场景，展现夏日的清凉与快乐。\r\n\r\n烧烤野餐：拍摄户外烧烤野餐的场景，展现家人或朋友们聚在一起享受美食和欢乐的情景。\r\n\r\n夏日运动：记录各种夏日运动项目，如沙滩排球、冲浪、滑水等，展现活力与激情。\r\n\r\n花园派对：拍摄在花园或户外举办的派对，展现欢乐、轻松的夏日氛围。\r\n\r\n', '张伟 王芳 李勇 刘洋', '在拍', '张伟');

-- ----------------------------
-- Table structure for props_tb
-- ----------------------------
DROP TABLE IF EXISTS `props_tb`;
CREATE TABLE `props_tb`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '道具id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '道具名',
  `inputdate` datetime NULL DEFAULT NULL COMMENT '入库时间',
  `context` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '道具介绍',
  `count` int NULL DEFAULT 1 COMMENT '道具数量',
  `imgurl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '道具图片路径',
  `type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '道具类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of props_tb
-- ----------------------------
INSERT INTO `props_tb` VALUES (1, '小黄花', '2024-02-07 11:47:17', '黄色的小花花，可以用来装饰', 3, 'http://localhost:8080/image/20240302114835f8.jpg', '花花');
INSERT INTO `props_tb` VALUES (4, '哆啦a梦灯牌', '2024-02-26 16:55:52', '哆啦a梦大灯牌，可以用来装饰场景', 30, 'http://localhost:8080/image/20240226165549爱思壁纸_681112.jpg', '布景');
INSERT INTO `props_tb` VALUES (5, '搞钱要紧灯牌', '2024-03-01 20:57:45', '搞钱要紧灯牌，大灯牌', 23, 'http://localhost:8080/image/20240301205742爱思壁纸_666225.jpg', '设备配件');

-- ----------------------------
-- Table structure for recordprop_tb
-- ----------------------------
DROP TABLE IF EXISTS `recordprop_tb`;
CREATE TABLE `recordprop_tb`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `propsname` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '道具名称',
  `propsid` int NULL DEFAULT NULL COMMENT '借出道具',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '借出用户',
  `count` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '借出数量',
  `state` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '借出状态',
  `outdate` datetime NULL DEFAULT NULL COMMENT '借出时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1813458951 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recordprop_tb
-- ----------------------------
INSERT INTO `recordprop_tb` VALUES (1813458948, 'asd', 4, '邱三', '3', '已归还', '2024-02-28 18:29:58');
INSERT INTO `recordprop_tb` VALUES (1813458950, 'asd', 4, '邱三', '4', '借出', '2024-02-28 18:31:39');

-- ----------------------------
-- Table structure for user_tb
-- ----------------------------
DROP TABLE IF EXISTS `user_tb`;
CREATE TABLE `user_tb`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户角色',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电子邮件地址',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1037 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_tb
-- ----------------------------
INSERT INTO `user_tb` VALUES (1033, 'kk', '$2a$10$GxF84wvGUL1uF/DbRnhOhORcQyAqFI4CsyamO79XEYGlyg.Zz8XVG', 'actorcontact', '1025321324@qq.com', '黎晓曼', '15378521557');
INSERT INTO `user_tb` VALUES (1034, '123', '$2a$10$HOJhwIk8esC6t22AvZxyBuf.TSulTtqTVddK5OWklGIdsEgJ6PaDu', 'admin', '102532234@qq.com', '邱三', '15426451527');
INSERT INTO `user_tb` VALUES (1035, 'admin', '$2a$10$bx2JU9O6m3symxb.cPgbMu41wIdo92B9/UFWpVTTwadnEJjHvK1.q', 'admin', '102533234@qq.com', '罗星', '13685233215');
INSERT INTO `user_tb` VALUES (1036, '21312', 'sdsad', 'propadmin', '10252323324@qq.com', '唐星散', '17607561021');

SET FOREIGN_KEY_CHECKS = 1;
