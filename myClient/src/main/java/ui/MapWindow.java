package ui;

import javax.swing.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.Bundle;
import models.Path;
import models.Place;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MapWindow extends JFrame{
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private JList <Bundle> bundleList = new JList<Bundle>(new DefaultListModel<Bundle>());
	private JScrollPane bundleScroll = new JScrollPane(bundleList);
	
	private JList <String> messageList = new JList<String>(new DefaultListModel<String>());
	private JScrollPane messageScroll = new JScrollPane(messageList);

	public MapWindow(){
		super("Map window");
		setLayout(new  BorderLayout());
	    setupPanels();
	    pack();
	    setVisible(true);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	class getBundlesListener implements ActionListener{
			
			public void actionPerformed(ActionEvent e){
				
				DefaultListModel<Bundle> bundleListModel = (DefaultListModel<Bundle>) bundleList.getModel();
				DefaultListModel<String> messageListModel = (DefaultListModel<String>) messageList.getModel();
				bundleListModel.clear();
				messageListModel.clear();
				
				Bundle[] bundles = Client.get();
				for(int i = 0; i < bundles.length; i++){
					bundleListModel.addElement(bundles[i]);
					try {
						messageListModel.addElement(mapper.writeValueAsString(bundles[i]));
					} catch (JsonProcessingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
		
		class InfoBundleListener implements ActionListener{
			
			public void actionPerformed(ActionEvent e){
				Bundle bundle = bundleList.getSelectedValue();
				
				JPanel panel = new JPanel();
				panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
				
				panel.add(new JLabel("Id: " + bundle.getId()));
				panel.add(new JLabel("name: " + bundle.getName()));
				panel.add(new JLabel("info: " + bundle.getInfo()));
				panel.add(new JLabel("image: " + bundle.getImage()));
				panel.add(new JLabel("paths " + bundle.getPaths().toString()));
				
				if(bundle.getPaths() != null){
					for(Path path : bundle.getPaths()){
						panel.add(new JLabel("Path: " + path.getId()));
						panel.add(new JLabel("Id: " + path.getId()));
						panel.add(new JLabel("name: " + path.getName()));
						panel.add(new JLabel("info: " + path.getInfo()));
						panel.add(new JLabel("length: " + path.getLength()));
						panel.add(new JLabel("polyline: " + path.getPolyline()));
						panel.add(new JLabel("duration: " + path.getDuration()));
						panel.add(new JLabel("image: " + path.getImage()));
					
						if(path.getPlaces() != null){
							for(Place place : path.getPlaces()){
								panel.add(new JLabel("Place " + "(" + place.getName() + ")"));
								panel.add(new JLabel("Name: " + place.getName()));
								panel.add(new JLabel("info: " + place.getInfo()));
								panel.add(new JLabel("Image: " + place.getImage()));
								panel.add(new JLabel("radius: " + place.getRadius()));
								panel.add(new JLabel("position: " + place.getPosition()));
								panel.add(new JLabel("Media: " + place.getName()));
							}
						}
					}
				}
				
				JOptionPane.showConfirmDialog(MapWindow.this, panel, "About Bundle", JOptionPane.CANCEL_OPTION);
			}
		}
		
		class EditBundleListener implements ActionListener{
			
			public void actionPerformed(ActionEvent e){
				
				Bundle bundle = bundleList.getSelectedValue();
				
				if(bundle == null){
					return;
				}	
				
				JPanel editPanel = new JPanel();
				editPanel.setLayout(new BoxLayout(editPanel,BoxLayout.Y_AXIS));
				
				JTextField name = new JTextField(bundle.getName(), 50); //TODO: put it in method instead
				JTextField info = new JTextField(bundle.getInfo(), 50);
				JTextField image = new JTextField(bundle.getImage(), 50);
				editPanel.add(new JLabel("Bundle"));
				editPanel.add(name);
				editPanel.add(info);
				editPanel.add(image);
				
				for(Path path : bundle.getPaths()){
					
					JButton pathButton = new JButton("Path " + path.getId());
					editPanel.add(pathButton);
					
					pathButton.addActionListener(new ActionListener(){
						
						public void actionPerformed (ActionEvent e){
							JTextField pathName = new JTextField(path.getName());
							JTextField pathInfo = new JTextField(path.getInfo());
							JTextField pathLength = new JTextField(path.getLength());
							JTextField pathPolyline = new JTextField("" + path.getPolyline());
							JTextField pathDuration =  new JTextField("" + path.getDuration());
							JTextField pathImage = new JTextField("" + path.getImage());
							
							JPanel pathPanel = new JPanel();
							pathPanel.add(new JLabel("Path " + path.getId()));
							pathPanel.add(pathName);
							pathPanel.add(pathInfo);
							pathPanel.add(pathLength);
							//pathPanel.add(polyLine); //TODO
							pathPanel.add(pathDuration);
							pathPanel.add(pathImage);
							
							int answer = JOptionPane.showConfirmDialog(MapWindow.this, pathPanel, "Edit Path", JOptionPane.OK_CANCEL_OPTION);
							if(answer == JOptionPane.YES_OPTION){
								path.setName(pathName.getText());
								path.setInfo(pathInfo.getText());
								path.setImage(pathLength.getText());
								//path.setPolyline(pathPolyline.getText()); 
								path.setDuration(pathDuration.getText());
								path.setImage(pathImage.getText());
							}
						}
					});
					
					for(Place place : path.getPlaces()){
						
						JButton placeButton = new JButton("Place " + place.getName());
						editPanel.add(placeButton);
						
						placeButton.addActionListener(new ActionListener(){
							
							public void actionPerformed (ActionEvent e){
								JTextField placeInfo = new JTextField("" + place.getInfo());
								JTextField placeImage = new JTextField(place.getImage());
								JTextField placeRadius = new JTextField(place.getRadius());
								JTextField placePosition = new JTextField("" + place.getPosition());
								JTextField placeMedia = new JTextField("" + place.getMedia());
								
								JPanel editPlace = new JPanel();
								editPlace.add(new JLabel("Place(" + path.getId() + "): " + place.getName()));
								editPlace.add(placeInfo);
								editPlace.add(placeImage);
								editPlace.add(placeRadius);
								editPlace.add(placePosition);
								editPlace.add(placeMedia);
								
								place.setInfo(placeInfo.getText());
								
								int answer = JOptionPane.showConfirmDialog(MapWindow.this, editPlace, "Edit Place", JOptionPane.OK_CANCEL_OPTION);
								if(answer == JOptionPane.YES_OPTION){
									place.setInfo(placeInfo.getText());
									place.setImage(placeImage.getText()); 
									place.setRadius(Integer.parseInt(placeRadius.getText()));
									//TODO position and media
								}
							}
						});
					
					}
				}
				
				int answer = JOptionPane.showConfirmDialog(MapWindow.this, editPanel, "Edit Bundle", JOptionPane.OK_CANCEL_OPTION);
				if(answer == JOptionPane.YES_OPTION){
					bundle.setName(name.getText());
					bundle.setInfo(info.getText());
					bundle.setImage(image.getText());
					
					
					Client.put(bundle);
					
					bundleList.updateUI();
				}
				
			}
		}
		
		class DeleteBundleListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				JPanel panel = new JPanel();
				panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
				panel.add(new JLabel("Id of bundle to be removed:"));
				JTextField idField = new JTextField("id", 10);
				panel.add(idField);
				
				Bundle bundle = bundleList.getSelectedValue();
				int answer = JOptionPane.showConfirmDialog(MapWindow.this, "Delete bundle?", "Delete", JOptionPane.YES_NO_OPTION);
            	if(answer == JOptionPane.YES_OPTION){
            		Client.delete(bundle.getId());
            		DefaultListModel<Bundle> listModel = (DefaultListModel<Bundle>) bundleList.getModel();
            		listModel.removeElement(bundle); //TODO Check if it was actually removed from database first
            	}
			}
		}
		
		class NewBundleListener implements ActionListener
		  {
			Bundle bundle;
			
			public NewBundleListener(){
				 bundle = new Bundle();
			}
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
				JPanel panel = new JPanel();
				panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
				panel.add(new JLabel("Name:"));
				JTextField nameField = new JTextField("name", 15);
				JTextField imageField = new JTextField("image path", 15);
				JTextField infoField = new JTextField("info", 15);
				
				panel.add(nameField);
				panel.add(imageField);
				panel.add(infoField);
				
				JButton newPathButton = new JButton("New Path");
				panel.add(newPathButton);
				newPathButton.addActionListener(new createPathListener());
				
				int answer = JOptionPane.showConfirmDialog(MapWindow.this, panel, "New Bundle", JOptionPane.OK_CANCEL_OPTION);
				if(answer == JOptionPane.YES_OPTION){
					bundle.setName(nameField.getText());
					bundle.setInfo(infoField.getText());
					bundle.setImage(imageField.getText());
					
					Bundle createdBundle = Client.post(bundle);
					((DefaultListModel<Bundle>) bundleList.getModel()).addElement(createdBundle);
						
				}
			}
			
			class createPathListener implements ActionListener{
				
				//TODO fields for places and polyline. Right now default is null for those.
				@Override
				public void actionPerformed(ActionEvent e) {
					JTextField nameField = new JTextField("name", 15);
					JTextField infoField = new JTextField("info", 15);
					JTextField lengthField = new JTextField("length", 15);
					JTextField durationField = new JTextField("duration", 15);
					JTextField imageField = new JTextField("image", 15);
					
					JPanel pathPanel = new JPanel();
					pathPanel.add(nameField);
					pathPanel.add(infoField);
					pathPanel.add(lengthField);
					pathPanel.add(durationField);
					pathPanel.add(imageField);
					
					int answer = JOptionPane.showConfirmDialog(MapWindow.this, pathPanel, "New Path", JOptionPane.OK_CANCEL_OPTION);
					if(answer == JOptionPane.YES_OPTION){
						Path createdPath = new Path(nameField.getText(), infoField.getText(), lengthField.getText(), null, durationField.getText(), imageField.getText(), null);
						bundle.addPath(createdPath);
							
					}
				}
				
			}
			
		  }
	
	
	public void setupPanels(){
		
		//West panel
		JPanel west = new JPanel();
		add(west, BorderLayout.WEST);
		west.setLayout(new  BorderLayout());
		west.add(new JLabel("JSON objects"), BorderLayout.NORTH);
	    west.add(messageScroll, BorderLayout.CENTER);
		
		//East panel
	    JPanel east = new JPanel();
		
		JButton infoBundle = new JButton("Show details");
		JButton getBundles = new JButton("GET bundles");
		JButton postBundle = new JButton("POST Bundle");
		JButton putBundle = new JButton("PUT bundle");
		JButton deleteBundle = new JButton("DELETE bundle");
		
		
		add(east, BorderLayout.EAST);
	    east.setLayout(new  BorderLayout());
	   
	    east.add(new JLabel("Bundles"), BorderLayout.NORTH);
	    east.add(bundleScroll, BorderLayout.CENTER);
	    
	    JPanel buttonPanel = new JPanel();
	    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
	    buttonPanel.add(infoBundle);
	    buttonPanel.add(getBundles);
	    buttonPanel.add(putBundle);
	    buttonPanel.add(deleteBundle);
	    buttonPanel.add(postBundle);
	    east.add(buttonPanel, BorderLayout.SOUTH);
	    
		getBundles.addActionListener(new getBundlesListener());
		deleteBundle.addActionListener(new DeleteBundleListener());
		infoBundle.addActionListener(new InfoBundleListener());
		putBundle.addActionListener(new EditBundleListener());
		postBundle.addActionListener(new NewBundleListener());
	}
	
	public static void main(String[]args){
		new MapWindow();
	}
}

