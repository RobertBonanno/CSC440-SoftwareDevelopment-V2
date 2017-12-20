package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map.Entry;

import backEnd.Member;
import backEnd.Provider;
import backEnd.Receipt;
import backEnd.ReceiptHash;
import backEnd.Service;

public class TransactionController extends BaseController{
	
	ReceiptHash receiptHash;
	
	public TransactionController() {
		super();
		receiptHash = new ReceiptHash(); 
		
	}
	//============Transactions==================
	
	public void CreateTransaction(Service service, Member member, Provider provider){
		
		Date date = new Date(System.currentTimeMillis()); 
		String comments;
		
		
		terminal.setOutput("Please enter in any comments about the service: ");
		comments = terminal.readText(); 
		int id = generateID();
		receiptHash.add(id, service, member, provider, date, comments);
		
		writeAcmeLog(id);
	}
	
	private Integer generateID(){
		Integer id; 
		while(true){
			id =(int) (Math.random()*1000.0); 
			if(receiptHash.search(id)!=null){
				continue; 
			}
			break; 
		}
		return id;
	}
	
	public void automatedAccounting(){
		memberReport();
		providerReport();
		summaryReport();
		eftReport();
	}
	
	@SuppressWarnings("deprecation")
	private void writeAcmeLog(int receiptID) {
		Receipt receipt = receiptHash.search(receiptID);
		String date = receipt.getDateOfService().getMonth()+"_"+receipt.getDateOfService().getDay()+"_"+receipt.getDateOfService().getYear();
		String fileName = receipt.getMember().getName()+date+".txt";
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
			Calendar calendar = Calendar.getInstance();
			Date fullDate = calendar.getTime();
			bw.write(fullDate+System.lineSeparator()+"Date of Service: ["+date+"]"
					+System.lineSeparator()+"Provider ID: "+receipt.getProvider().idToString()
					+System.lineSeparator()+"Member ID: "+receipt.getMember().idToString()
					+System.lineSeparator()+"Service ID: "+receipt.getService().idToString()
					+System.lineSeparator()+"Comments: "+receipt.getComments());
			bw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void memberReport(){
		Date today = new Date(System.currentTimeMillis());
		String date = today.getMonth()+"_"+today.getDay();
		ArrayList<Entry<Integer, ArrayList<Receipt>>> memberListOfServices = receiptHash.transformToMemberKey();
		for(Entry<Integer, ArrayList<Receipt>> entry : memberListOfServices){
			String mName = entry.getValue().get(0).getMember().getName();
			String fileName = mName	+"'s Service Report " + date+".txt";
			
			new File(fileName);
			
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
				bw.write(mName+","+System.lineSeparator()+"Your recorded services recieved during the week of "+date
						+" are as follows:"+System.lineSeparator());
				bw.write(entry.getValue().get(0).getMember().toString()+System.lineSeparator());
				bw.write("------------------------------"+System.lineSeparator());
				for(Receipt receipt : entry.getValue()){
					bw.write("Service: ["+receipt.getService().getName()+"] On ["+receipt.getDateOfService()
					+"] from ["+receipt.getProvider().getName()+"]"+System.lineSeparator());
					bw.write("------------------------------"+System.lineSeparator());
				}
				
				bw.close();
			} catch(IOException e){
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void providerReport(){
		
		Date today = new Date(System.currentTimeMillis());
		String date = today.getMonth()+"_"+today.getDay();
		ArrayList<Entry<Integer, ArrayList<Receipt>>> providerListOfServices = receiptHash.transformToProviderKey();
		
		for(Entry<Integer, ArrayList<Receipt>> entry : providerListOfServices){
			Provider provider = entry.getValue().get(0).getProvider();
			Double totalFee = 0.0;
			
			String pName = provider.getName();
			String fileName = pName	+"'s Service Report " + date+".txt";
			
			new File(fileName);
			
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
				bw.write(pName+","+System.lineSeparator()+"Your recorded services provided during the week of "
						+date+" are as follows:"+System.lineSeparator());
				
				bw.write(provider.getName()+"-"+provider.getID()+"-"+System.lineSeparator()+"Work Address: "
						+provider.getAddress()+System.lineSeparator());
				bw.write("------------------------------"+System.lineSeparator());
				
				for(Receipt receipt : entry.getValue()){
					Date received = receipt.getDateOfService();
					Service service = receipt.getService();
					totalFee+=service.getFee();
					
					bw.write("Date of Service: ["+received.getMonth()+"-"+received.getDate()+"] On ["
							+receipt.getDateOfService().toString()+"] to ["
							+receipt.getMember().getName()+"-"+receipt.getMember().getID()+"] "+"Service Code: ["
							+service.getID()+"] Fee for service: "+service.getFee()+System.lineSeparator());
					bw.write("------------------------------"+System.lineSeparator());
				}
				bw.write("Total Consultations: "+entry.getValue().size()+"\tTotal Fees: "+totalFee);
				bw.close();
			} catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void summaryReport(){
		
		Date today = new Date(System.currentTimeMillis());
		String date = today.getMonth()+"_"+today.getDay();
		ArrayList<Entry<Integer, ArrayList<Receipt>>> providerListOfServices = receiptHash.transformToProviderKey();
		
		String fileName = "Summary Report " +date+ ".txt";
		new File(fileName);
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
			int summaryNumConsultations = 0;
			double summaryFee = 0.0;
			
			bw.write("Chocohalics Anonymous summary Report for the week of: "+date
					+" are as follows:"+System.lineSeparator());
			bw.write("------------------------------"+System.lineSeparator());	
			
			for(Entry<Integer, ArrayList<Receipt>> entry : providerListOfServices){
				
				double providerFee = 0.0;
				Provider provider = entry.getValue().get(0).getProvider();
				
				bw.write(provider.getName()+": "+entry.getValue().size());
				
				for(Receipt receipt : entry.getValue()){
					providerFee+=receipt.getService().getFee();
				}
				bw.write("\tProvider's Fee: "+providerFee+System.lineSeparator());
				bw.write("------------------------------"+System.lineSeparator());
				summaryFee+=providerFee;
				summaryNumConsultations+=entry.getValue().size();
			}
			bw.write("Total fee: ["+summaryFee+"] Total number of consultations: ["+summaryNumConsultations+"]");
			bw.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void eftReport() {
		terminal.setOutput("EFT Report is to be implemented.");
	}

	public void writeToXML() {
		receiptHash.writeToXML();
	}

}