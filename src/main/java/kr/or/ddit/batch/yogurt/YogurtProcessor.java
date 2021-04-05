package kr.or.ddit.batch.yogurt;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import kr.or.ddit.yogurt.model.CycleVo;
import kr.or.ddit.yogurt.model.DailyVo;

public class YogurtProcessor implements ItemProcessor<CycleVo, List<DailyVo>>{
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	@Value("#{jobParameters[dt]}")
	private Date dt;
	
	// dt : 202102, item : cid-1, pid-100 ,day-2, cnt-1
	// ==> 
	//					   cid-1, pid-100 ,dt-20210201, cnt-1
	//					   cid-1, pid-100 ,dt-20210208, cnt-1
	//					   cid-1, pid-100 ,dt-20210215, cnt-1
	//					   cid-1, pid-100 ,dt-20210222, cnt-1
	
	// 1��~28��  loop
	// if (���� == item.���ϰ� ������ üũ) 
	// 		�ش� ���ڷ� �Ͻ��� �����͸� ����
	
	// �ش����� ������ ��¥ (date)
	// �ش����� ù��° ��¥ - 1�� (date)
	
	
	@Override
	public List<DailyVo> process(CycleVo item) throws Exception {
		//���� ��¥ �ð�
		Calendar calender = Calendar.getInstance();
		
		calender.setTime(dt);
		calender.set(Calendar.DAY_OF_MONTH, calender.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date endDt = calender.getTime();
		//20210228 02:00:00
		
		calender.set(Calendar.DAY_OF_MONTH, 1);
		calender.set(Calendar.HOUR_OF_DAY, 0);
	
		//20210201 02:00:00
		//Date startDt = calender.getTime();
		List<DailyVo> dailyVoList = new ArrayList<DailyVo>();
		
		while(endDt.compareTo(calender.getTime()) > 0 ) {
			
			//20210201 ==> �ְ�����
			if(item.getDay() == calender.get(Calendar.DAY_OF_WEEK) ){
				//cid, pid, dt(yyyyMMdd), cnt 
				dailyVoList.add( new DailyVo(item.getCid(),
											 item.getPid(),
										     sdf.format(calender.getTime()), 
											 item.getCnt()));
			}
			
			calender.set(Calendar.DAY_OF_MONTH, calender.get(Calendar.DAY_OF_MONTH) +1);
		}
		
		return dailyVoList;
	}


	
}
