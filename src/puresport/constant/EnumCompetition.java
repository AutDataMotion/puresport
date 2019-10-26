package puresport.constant;

public enum EnumCompetition {
	ShengYunHui(0,"0","省运会"),
	YaYunHui(1,"1","亚运会"),
	QingAoHui(2,"2","青奥会"),
	JunYunHui(3,"3","军运会"),
	DongJingAoYunHui(4,"4","东京奥运会"),
	;
	private int index;
	private String index_str;
	private String competitionName;
	private EnumCompetition(int index,String index_str,String competitionName)
	{
		this.index = index;
		this.index_str = index_str;
		this.competitionName = competitionName;
	}
	public void setIndex(int index)
	{
		this.index = index;
	}
	public int getIndex()
	{
		return this.index;
	}
	public void setIndex_str(String index_str)
	{
		this.index_str = index_str;
	}
	public String getIndex_str()
	{
		return this.index_str;
	}
	
	public void setCompetitionName(String competitionName)
	{
		this.competitionName = competitionName;
	}
	public String getCompetitionName()
	{
		return this.competitionName;
	}
}
