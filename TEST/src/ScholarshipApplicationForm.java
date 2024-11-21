	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.util.Random;
	
	public class ScholarshipApplicationForm extends JFrame {
	    private static final long serialVersionUID = 1L; // 
	
	    private JTextField inputName, inputStudentID, inputNumber, inputEmail, inputGPA, inputCredits, inputCAPTCHA, essayField;
	    private JLabel labelCAPTCHA, labelName, labelStudentID, labelNumber, labelEmail, labelGPA, labelCredits, labelEssay, lblNewLabel;
	    private int captchaAnswer;
	    private JPanel westPanel, eastPanel;
	    private JButton submitButton;
	   
	
	    public ScholarshipApplicationForm() {
	        // Set up the frame
	        setTitle("SKKU Scholarship Application Form");
	        setSize(850, 650);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        getContentPane().setLayout(new BorderLayout());
	        getContentPane().setBackground(Color.WHITE);
	
	        // Left Panel for Image
	        westPanel = new JPanel();
	        westPanel.setBackground(Color.WHITE);
	        westPanel.setLayout(new BorderLayout());
	        westPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	
	        // Load and scale image for banner
	        ImageIcon imageIcon = new ImageIcon("C:\\Users\\jando\\Downloads\\assignment4_instructions\\banner.png");
	        Image image = imageIcon.getImage().getScaledInstance(420, 580, Image.SCALE_SMOOTH);
	        imageIcon = new ImageIcon(image);
	        JLabel bannerLabel = new JLabel(imageIcon);
	        westPanel.add(bannerLabel, BorderLayout.CENTER);
	        getContentPane().add(westPanel, BorderLayout.WEST);
	
	        // Right Panel for Form Fields
	        eastPanel = new JPanel();
	        eastPanel.setBackground(Color.WHITE);
	        eastPanel.setLayout(new GridLayout(10, 2, 5, 5));
	        eastPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	
	        // Add labels and text fields to eastPanel
	        addFormFields();
	
	        // Initialize CAPTCHA label and generate CAPTCHA
	        labelCAPTCHA = new JLabel("CAPTCHA: ");
	        labelCAPTCHA.setHorizontalAlignment(SwingConstants.RIGHT);
	        generateCaptcha();
	        eastPanel.add(labelCAPTCHA);
	
	        // Add the right panel to the frame
	        getContentPane().add(eastPanel, BorderLayout.CENTER);
	        
	                inputCAPTCHA = new JTextField();
	                eastPanel.add(inputCAPTCHA);
	                
	                        // Submit button
	                        submitButton = new JButton("Submit");
	                        submitButton.setBackground(new Color(188, 208, 232));
	                        submitButton.addActionListener(new SubmitButtonListener());
	                        
	                        lblNewLabel = new JLabel("");
	                        eastPanel.add(lblNewLabel);
	                        eastPanel.add(submitButton);
	    }
	
	    // Method to add form labels and text fields
	    private void addFormFields() {
	        labelName = new JLabel("Name:");
	        labelName.setHorizontalAlignment(SwingConstants.RIGHT);
	        eastPanel.add(labelName);
	        inputName = new JTextField();
	        eastPanel.add(inputName);
	
	        labelStudentID = new JLabel("Student ID:");
	        labelStudentID.setHorizontalAlignment(SwingConstants.RIGHT);
	        eastPanel.add(labelStudentID);
	        inputStudentID = new JTextField();
	        eastPanel.add(inputStudentID);
	
	        labelNumber = new JLabel("Phone:");
	        labelNumber.setHorizontalAlignment(SwingConstants.RIGHT);
	        eastPanel.add(labelNumber);
	        inputNumber = new JTextField();
	        eastPanel.add(inputNumber);
	
	        labelEmail = new JLabel("Email:");
	        labelEmail.setHorizontalAlignment(SwingConstants.RIGHT);
	        eastPanel.add(labelEmail);
	        inputEmail = new JTextField();
	        eastPanel.add(inputEmail);
	
	        labelGPA = new JLabel("GPA:");
	        labelGPA.setHorizontalAlignment(SwingConstants.RIGHT);
	        eastPanel.add(labelGPA);
	        inputGPA = new JTextField();
	        eastPanel.add(inputGPA);
	
	        labelCredits = new JLabel("Credits:");
	        labelCredits.setHorizontalAlignment(SwingConstants.RIGHT);
	        eastPanel.add(labelCredits);
	        inputCredits = new JTextField();
	        eastPanel.add(inputCredits);
	
	        labelEssay = new JLabel("Essay:");
	        labelEssay.setHorizontalAlignment(SwingConstants.RIGHT);
	        eastPanel.add(labelEssay);
	        essayField = new JTextField();
	        eastPanel.add(essayField);
	    }
	
	    // CAPTCHA generation method
	    private void generateCaptcha() {
	        Random rand = new Random();
	        int num1 = rand.nextInt(100);
	        int num2 = rand.nextInt(100);
	        captchaAnswer = num1 + num2;
	        labelCAPTCHA.setText("CAPTCHA: " + num1 + " + " + num2 + " = ?");
	    }
	
	    // Validation methods for each field
	    private void validateName() throws ValidationException {
	        if (!inputName.getText().matches("[A-Za-z]+ [A-Za-z]+")) {
	            throw new ValidationException("1. First or last name is missing.");
	        }
	    }
	
	    private void validateStudentID() throws ValidationException {
	        if (!inputStudentID.getText().matches("\\d{10}")) {
	            throw new ValidationException("2. Student ID format is wrong (ex: 20227128xx).");
	        }
	    }
	
	    private void validatePhone() throws ValidationException {
	        if (!inputNumber.getText().matches("\\+8210\\d{8}")) {
	            throw new ValidationException("3. Phone number format is wrong (ex: +8210xxxxxxxx).");
	        }
	    }
	
	    private void validateEmail() throws ValidationException {
	        if (!inputEmail.getText().matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}")) {
	            throw new ValidationException("4. Email format is wrong (ex: some@some.com).");
	        }
	    }
	
	    private void validateGPA() throws ValidationException {
	        try {
	            double gpa = Double.parseDouble(inputGPA.getText());
	            if (gpa < 0.0 || gpa > 4.5) {
	                throw new ValidationException("5. GPA must be between 0.0 and 4.5.");
	            }
	        } catch (NumberFormatException e) {
	            throw new ValidationException("5. GPA must be a valid decimal number.");
	        }
	    }
	
	    private void validateCredits() throws ValidationException {
	        try {
	            int credits = Integer.parseInt(inputCredits.getText());
	            if (credits < 12) {
	                throw new ValidationException("6. Number of credits must be above or equal to 12.");
	            }
	        } catch (NumberFormatException e) {
	            throw new ValidationException("6. Credits must be an integer.");
	        }
	    }
	
	    private void validateEssay() throws ValidationException {
	        if (essayField.getText().length() < 100 || essayField.getText().length() > 200) {
	            throw new ValidationException("7. Essay length must be between 100 and 200 characters.");
	        }
	    }
	
	    private void validateCaptcha() throws ValidationException {
	        try {
	            int captchaValue = Integer.parseInt(inputCAPTCHA.getText());
	            if (captchaValue != captchaAnswer) {
	                throw new ValidationException("8. CAPTCHA is wrong.");
	            }
	        } catch (NumberFormatException e) {
	            throw new ValidationException("8. CAPTCHA must be a valid number.");
	        }
	    }
	
	    // Clears all fields and resets CAPTCHA and colors
	    private void clearFields() {
	        inputName.setText("");
	        inputStudentID.setText("");
	        inputNumber.setText("");
	        inputEmail.setText("");
	        inputGPA.setText("");
	        inputCredits.setText("");
	        essayField.setText("");
	        inputCAPTCHA.setText("");
	        generateCaptcha();
	        resetColors();
	    }
	
	    // Resets field colors to black
	    private void resetColors() {
	        inputName.setForeground(Color.BLACK);
	        inputStudentID.setForeground(Color.BLACK);
	        inputNumber.setForeground(Color.BLACK);
	        inputEmail.setForeground(Color.BLACK);
	        inputGPA.setForeground(Color.BLACK);
	        inputCredits.setForeground(Color.BLACK);
	        essayField.setForeground(Color.BLACK);
	        inputCAPTCHA.setForeground(Color.BLACK);
	    }
	
	    // SubmitButtonListener handles validation on submit button click
	    private class SubmitButtonListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	            StringBuilder errors = new StringBuilder();
	            resetColors();
	
	            try {
	                validateName();
	            } catch (ValidationException ex) {
	                errors.append(ex.getMessage()).append("\n");
	                inputName.setForeground(Color.RED);
	            }
	
	            try {
	                validateStudentID();
	            } catch (ValidationException ex) {
	                errors.append(ex.getMessage()).append("\n");
	                inputStudentID.setForeground(Color.RED);
	            }
	
	            try {
	                validatePhone();
	            } catch (ValidationException ex) {
	                errors.append(ex.getMessage()).append("\n");
	                inputNumber.setForeground(Color.RED);
	            }
	
	            try {
	                validateEmail();
	            } catch (ValidationException ex) {
	                errors.append(ex.getMessage()).append("\n");
	                inputEmail.setForeground(Color.RED);
	            }
	
	            try {
	                validateGPA();
	            } catch (ValidationException ex) {
	                errors.append(ex.getMessage()).append("\n");
	                inputGPA.setForeground(Color.RED);
	            }
	
	            try {
	                validateCredits();
	            } catch (ValidationException ex) {
	                errors.append(ex.getMessage()).append("\n");
	                inputCredits.setForeground(Color.RED);
	            }
	
	            try {
	                validateEssay();
	            } catch (ValidationException ex) {
	                errors.append(ex.getMessage()).append("\n");
	                essayField.setForeground(Color.RED);
	            }
	
	            try {
	                validateCaptcha();
	            } catch (ValidationException ex) {
	                errors.append(ex.getMessage()).append("\n");
	                inputCAPTCHA.setForeground(Color.RED);
	            }
	
	            if (errors.length() > 0) {
	                JOptionPane.showMessageDialog(ScholarshipApplicationForm.this, errors.toString(), "Error", JOptionPane.ERROR_MESSAGE);
	            } else {
	                JOptionPane.showMessageDialog(ScholarshipApplicationForm.this, "Application Submitted Successfully!");
	                clearFields();
	            }
	        }
	    }
	
	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            ScholarshipApplicationForm form = new ScholarshipApplicationForm();
	            form.setVisible(true);
	        });
	    }
	}
	
	
	class ValidationException extends Exception {
	    private static final long serialVersionUID = 1L;
	
	    public ValidationException(String message) {
	        super(message);
	    }
	}
