package com.dxtd.monitor;

public class Statement {
	private Double used_cpu_sys;
	private Double used_cpu_user;
	private Double blocked_clients;
	private Double connected_clients;
	private Double used_memory;
	private Double used_memory_rss;
	public Double getUsed_cpu_sys() {
		return used_cpu_sys;
	}
	public void setUsed_cpu_sys(Double used_cpu_sys) {
		this.used_cpu_sys = used_cpu_sys;
	}
	public Double getUsed_cpu_user() {
		return used_cpu_user;
	}
	public void setUsed_cpu_user(Double used_cpu_user) {
		this.used_cpu_user = used_cpu_user;
	}
	public Double getBlocked_clients() {
		return blocked_clients;
	}
	public void setBlocked_clients(Double blocked_clients) {
		this.blocked_clients = blocked_clients;
	}
	public Double getConnected_clients() {
		return connected_clients;
	}
	public void setConnected_clients(Double connected_clients) {
		this.connected_clients = connected_clients;
	}
	public Double getUsed_memory() {
		return used_memory;
	}
	public void setUsed_memory(Double used_memory) {
		this.used_memory = used_memory;
	}
	public Double getUsed_memory_rss() {
		return used_memory_rss;
	}
	public void setUsed_memory_rss(Double used_memory_rss) {
		this.used_memory_rss = used_memory_rss;
	}
	@Override
	public String toString() {
		return "Statement [used_cpu_sys=" + used_cpu_sys + ", used_cpu_user=" + used_cpu_user + ", blocked_clients="
				+ blocked_clients + ", connected_clients=" + connected_clients + ", used_memory=" + used_memory
				+ ", used_memory_rss=" + used_memory_rss + "]";
	}

	
}
