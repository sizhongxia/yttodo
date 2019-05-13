package com.company.project.common.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * <p>
 * 名称：IdWorker.java
 * </p>
 * <p>
 * 描述：分布式自增长ID
 * </p>
 * 
 * <pre>
 *     Twitter的 Snowflake　JAVA实现方案
 * </pre>
 * 
 * 核心代码为其IdWorker这个类实现，其原理结构如下，我分别用一个0表示一位，用—分割开部分的作用： 1||0---0000000000
 * 0000000000 0000000000 0000000000 0 --- 00000 ---00000 ---000000000000
 * 在上面的字符串中，第一位为未使用（实际上也可作为long的符号位），接下来的41位为毫秒级时间，
 * 然后5位datacenter标识位，5位机器ID（并不算标识符，实际是为线程标识），
 * 然后12位该毫秒内的当前毫秒内的计数，加起来刚好64位，为一个Long型。
 * 这样的好处是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞（由datacenter和机器ID作区分），
 * 并且效率较高，经测试，snowflake每秒能够产生26万ID左右，完全满足需要。
 * <p>
 * 64位ID (42(毫秒)+5(机器ID)+5(业务编码)+12(重复累加))
 */
public class IdWorker {
	// 机器标识位数
	private final static long workerIdBits = 5L;
	// 数据中心标识位数
	private final static long datacenterIdBits = 5L;
	// 机器ID最大值
	private final static long maxWorkerId = -1L ^ (-1L << workerIdBits);
	// 数据中心ID最大值
	private final static long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

	private Snowflake snowflake = null;

	// public IdWorker() {
	// long datacenterId = getDatacenterId(maxDatacenterId);
	// long workerId = getMaxWorkerId(datacenterId, maxWorkerId);
	// this.snowflake = IdUtil.createSnowflake(workerId, datacenterId);
	// }

	/**
	 * @param workerId
	 *            工作机器ID
	 * @param datacenterId
	 *            序列号
	 */
	public IdWorker(long workerId, long datacenterId) {
		if (workerId > maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
		}
		if (datacenterId > maxDatacenterId || datacenterId < 0) {
			throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
		}
		this.snowflake = IdUtil.createSnowflake(workerId, datacenterId);
	}

	/**
	 * 获取下一个ID
	 *
	 * @return
	 */
	public synchronized long nextId() {
		return snowflake.nextId();
	}

	/**
	 * <p>
	 * 获取 maxWorkerId
	 * </p>
	 */
	// protected static long getMaxWorkerId(long datacenterId, long maxWorkerId) {
	// StringBuffer mpid = new StringBuffer();
	// mpid.append(datacenterId);
	// String name = ManagementFactory.getRuntimeMXBean().getName();
	// if (!name.isEmpty()) {
	// /*
	// * GET jvmPid
	// */
	// mpid.append(name.split("@")[0]);
	// }
	// /*
	// * MAC + PID 的 hashcode 获取16个低位
	// */
	// return (mpid.toString().hashCode() & 0xffff) % (maxWorkerId + 1);
	// }

	/**
	 * <p>
	 * 数据标识id部分
	 * </p>
	 */
	// protected static long getDatacenterId(long maxDatacenterId) {
	// long id = 0L;
	// try {
	// InetAddress ip = InetAddress.getLocalHost();
	// NetworkInterface network = NetworkInterface.getByInetAddress(ip);
	// if (network == null) {
	// id = 1L;
	// } else {
	// byte[] mac = network.getHardwareAddress();
	// id = ((0x000000FF & (long) mac[mac.length - 1])
	// | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
	// id = id % (maxDatacenterId + 1);
	// }
	// } catch (Exception e) {
	// System.out.println(" getDatacenterId: " + e.getMessage());
	// }
	// return id;
	// }

	public static void main(String[] args) {
		// IdWorker idWorker = new IdWorker(31,31);
		// System.out.println("idWorker="+idWorker.nextId());
		IdWorker id = new IdWorker(0, 1);
		// System.out.println("id="+id.nextId());
		// System.out.println(id.datacenterId);
		// System.out.println(id.workerId);

		// for (int i = 0; i < 10; i++) {
		// System.err.println("E" + id.nextId());
		// }
		for (int i = 0; i < 10; i++) {
			System.out.println(id.nextId());
		}
		System.out.println((id.nextId() + "").length());
	}
}