package controller;

import java.awt.BufferCapabilities;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
		
		receiptHash.add(service, member, provider, date, comments);
		
	}
	
	public void AutomatedAccounting(){
		
		
		
	}
	
	public void WriteACMELog(){
		
	}
	
	public void memberReport(){
		Date today = new Date(System.currentTimeMillis());
		String date = today.getMonth()+"_"+today.getDay();
		ArrayList<Entry<Integer, ArrayList<Receipt>>> memberListOfServices = receiptHash.transformToMemberKey();
		for(Entry<Integer, ArrayList<Receipt>> entry : memberListOfServices){
			System.out.println("----------------"+entry.getValue().size());
			String mName = entry.getValue().get(0).getMember().getName();
			String fileName = mName	+"'s Service Report " + date+".txt";
			
			File file = new File(fileName);
			
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
				bw.write(mName+","+System.lineSeparator()+"Your recorded services recieved during the week of "+date+" are as follows:"+System.lineSeparator());
				bw.write(entry.getValue().get(0).getMember().toString()+System.lineSeparator());
				bw.write("------------------------------"+System.lineSeparator());
				for(Receipt receipt : entry.getValue()){
					bw.write("Service: ["+receipt.getService().getName()+"] On ["+receipt.getDateOfService()+"] from ["+receipt.getProvider().getName()+"]"+System.lineSeparator());
					bw.write("------------------------------"+System.lineSeparator());
				}
				
				bw.close();
			} catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public void providerReport(){
		//sum of provider's work
		//kappa pasta memberReport
		//Singular: info to include: Provideer name-ID-\nAddress
		//Every:    [Date of service] [Date and time received] [Member Name-ID] [Service code] [fee]
		//Singular: [total consultations] [total fee]
	}
	
	public void summaryReport(){
		//
		//Every: provider name: number of consultations [ ] total fee [ ]
		//Final: total number of consultations [ ] sum of total fees [ ]
	}
	
	public void EFTReport() {
		terminal.setOutput("EFT Report is to be implemented.");
	}

}