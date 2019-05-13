package com.company.project.common.utils;

import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.util.RandomUtil;

public class IdUtils {
//	public static String initUuid() {
//		return IdUtil.simpleUUID();
//	}

	public static String initObjectId() {
		return ObjectId.next() + RandomUtil.randomString("0123456789abcdef", 6);
	}

	 public static void main(String[] args) {
	 for (int i = 0; i < 10; i++) {
	 System.out.println(initObjectId());
	 }
	 }
	// System.out.println(initObjectId());
	// System.out.println(initObjectId());
	// System.out.println(initObjectId());
	// System.out.println(initObjectId());
	// System.out.println(initObjectId());
	// System.out.println(initObjectId());
	// System.out.println(initObjectId().length());
	// Snowflake snowflake = IdUtil.createSnowflake(1, 1);
	// long id = snowflake.nextId();
	// System.out.println((id+""));
	// 1076768774914445312
	// 1076768846813204480
	// 1076769159385321472

	// String json = FileUtil.readString("D:\\s.txt", "GBK");
	// JSONArray jsonArr = JSON.parseArray(json);
	// JSONArray dates = jsonArr.getJSONArray(0);
	// JSONArray types = jsonArr.getJSONArray(1);
	// JSONArray values = jsonArr.getJSONArray(2);
	// // System.out.println(dates);
	// // System.out.println(types);
	// // System.out.println(values);
	// // System.err.println(DateUtil.parse("2019年04月19日", "yyyy年MM月dd日"));
	// int size = dates.size();
	// int index = 0;
	//
	// for (int i = 0; i < size; i = i + 3) {
	// if (index >= 1500) {
	// String sql = "INSERT INTO `smart_culture_moa_wpap_200dzs`" + "
	// (`res_id`, " + "`res_date`, `ncp200`, `lycp`,`clzcp`, `xcp`,
	// `scp`,`sc`, `sg`, `ls`, `syy`," + " `clzcp_cv`,`xcp_cv`, `ls_cv`,
	// `sg_cv`, `lycp_cv`, `sc_cv`, `scp_cv`, `ncp200_cv`, `syy_cv`,
	// `create_at`) VALUES" + " ('" + initObjectId() + "', '" +
	// DateUtil.format(DateUtil.parse(dates.getString(i), "yyyy年MM月dd日"),
	// "yyyy-MM-dd") + "', " + "'" + values.getBigDecimal((index * 3) + 0) +
	// "', '" + values.getBigDecimal((index * 3) + 1) + "', '" +
	// values.getBigDecimal((index * 3) + 2) + "', '0', '0', '0', '0', '0',
	// '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '2019-04-19
	// 18:13:15');";
	// System.out.println(sql);
	// }
	// index++;
	// if (index == 1700) {
	// break;
	// }
	// }

	// }
}
