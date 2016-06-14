package kr.co.stardust.domain;

import java.io.Serializable;

public class EmpBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -486902103025402461L;
	private int empno;
	private String ename;
	private int mgr;
	private String job;
	private String hiredate;
	private int sal;
	private int comm;
	private int deptno;
	
	public int getEmpno() {
		return empno;
	}
	
	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public int getMgr() {
		return mgr;
	}

	public void setMgr(int mgr) {
		this.mgr = mgr;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public int getComm() {
		return comm;
	}

	public void setComm(int comm) {
		this.comm = comm;
	}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	@Override
	public String toString() {
		return "사원번호: " + empno + "/사원이름: " + ename + "/매니저번호: " + mgr + "/업무명: " + job + "/입사일자: " + hiredate + "/월급: " + sal + "/수당: " + comm + "/부서번호" + deptno; 
	}
	
	
}
