import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class MarketingManagementSystem {
    
    public static void main(String[] args) {
        MarketingManagementSystem app = new MarketingManagementSystem();
        app.start();
    }

    // Main method to display the menu and manage options
    public void start() {
        CustomerManager customerManager = new CustomerManager();
        CampaignManager campaignManager = new CampaignManager();
        ReportManager reportManager = new ReportManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Marketing Management System ===");
            System.out.println("1. Manage Customers");
            System.out.println("2. Manage Campaigns");
            System.out.println("3. View Reports");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    customerManager.manageCustomers();
                    break;
                case 2:
                    campaignManager.manageCampaigns(customerManager);
                    break;
                case 3:
                    reportManager.viewReports(campaignManager);
                    break;
                case 4:
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Customer management with enhanced features
    class CustomerManager {
        private ArrayList<Customer> customers = new ArrayList<>();
        private Scanner scanner = new Scanner(System.in);

        public void manageCustomers() {
            while (true) {
                System.out.println("\n=== Customer Management ===");
                System.out.println("1. Add Customer");
                System.out.println("2. Edit Customer");
                System.out.println("3. Delete Customer");
                System.out.println("4. View Customers");
                System.out.println("5. Search Customer by Email");
                System.out.println("6. Back to Main Menu");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        addCustomer();
                        break;
                    case 2:
                        editCustomer();
                        break;
                    case 3:
                        deleteCustomer();
                        break;
                    case 4:
                        viewCustomers();
                        break;
                    case 5:
                        searchCustomer();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }

        private void addCustomer() {
            System.out.print("Enter customer name: ");
            String name = scanner.nextLine();
            System.out.print("Enter customer email: ");
            String email = scanner.nextLine();

            if (name.isEmpty() || email.isEmpty()) {
                System.out.println("Error: Name and email cannot be empty.");
                return;
            }

            customers.add(new Customer(name, email));
            System.out.println("Customer added successfully.");
        }

        private void editCustomer() {
            System.out.print("Enter the email of the customer to edit: ");
            String email = scanner.nextLine();
            Customer customer = findCustomerByEmail(email);

            if (customer == null) {
                System.out.println("Customer not found.");
                return;
            }

            System.out.print("Enter new name (leave empty to keep current): ");
            String newName = scanner.nextLine();
            System.out.print("Enter new email (leave empty to keep current): ");
            String newEmail = scanner.nextLine();

            if (!newName.isEmpty()) {
                customer.setName(newName);
            }
            if (!newEmail.isEmpty()) {
                customer.setEmail(newEmail);
            }

            System.out.println("Customer updated successfully.");
        }

        private void deleteCustomer() {
            System.out.print("Enter the email of the customer to delete: ");
            String email = scanner.nextLine();
            Customer customer = findCustomerByEmail(email);

            if (customer != null) {
                customers.remove(customer);
                System.out.println("Customer deleted successfully.");
            } else {
                System.out.println("Customer not found.");
            }
        }

        private void viewCustomers() {
            System.out.println("\n=== Customer List ===");
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }

        private void searchCustomer() {
            System.out.print("Enter customer email to search: ");
            String email = scanner.nextLine();
            Customer customer = findCustomerByEmail(email);

            if (customer != null) {
                System.out.println(customer);
            } else {
                System.out.println("Customer not found.");
            }
        }

        private Customer findCustomerByEmail(String email) {
            for (Customer customer : customers) {
                if (customer.getEmail().equals(email)) {
                    return customer;
                }
            }
            return null;
        }
    }

    // Campaign management with scheduling and editing
    class CampaignManager {
        private ArrayList<Campaign> campaigns = new ArrayList<>();
        private Scanner scanner = new Scanner(System.in);

        public void manageCampaigns(CustomerManager customerManager) {
            while (true) {
                System.out.println("\n=== Campaign Management ===");
                System.out.println("1. Create Campaign");
                System.out.println("2. Edit Campaign");
                System.out.println("3. Delete Campaign");
                System.out.println("4. View Campaigns");
                System.out.println("5. Send Campaigns");
                System.out.println("6. Back to Main Menu");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        createCampaign();
                        break;
                    case 2:
                        editCampaign();
                        break;
                    case 3:
                        deleteCampaign();
                        break;
                    case 4:
                        viewCampaigns();
                        break;
                    case 5:
                        sendCampaigns(customerManager);
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }

        private void createCampaign() {
            System.out.print("Enter campaign title: ");
            String title = scanner.nextLine();
            System.out.print("Enter campaign message: ");
            String message = scanner.nextLine();

            if (title.isEmpty() || message.isEmpty()) {
                System.out.println("Error: Title and message cannot be empty.");
                return;
            }

            System.out.print("Enter schedule date (YYYY-MM-DD): ");
            String scheduleDate = scanner.nextLine();

            campaigns.add(new Campaign(title, message, scheduleDate));
            System.out.println("Campaign created successfully.");
        }

        private void editCampaign() {
            System.out.print("Enter the title of the campaign to edit: ");
            String title = scanner.nextLine();
            Campaign campaign = findCampaignByTitle(title);

            if (campaign == null) {
                System.out.println("Campaign not found.");
                return;
            }

            System.out.print("Enter new title (leave empty to keep current): ");
            String newTitle = scanner.nextLine();
            System.out.print("Enter new message (leave empty to keep current): ");
            String newMessage = scanner.nextLine();
            System.out.print("Enter new schedule date (leave empty to keep current): ");
            String newScheduleDate = scanner.nextLine();

            if (!newTitle.isEmpty()) {
                campaign.setTitle(newTitle);
            }
            if (!newMessage.isEmpty()) {
                campaign.setMessage(newMessage);
            }
            if (!newScheduleDate.isEmpty()) {
                campaign.setScheduleDate(newScheduleDate);
            }

            System.out.println("Campaign updated successfully.");
        }

        private void deleteCampaign() {
            System.out.print("Enter the title of the campaign to delete: ");
            String title = scanner.nextLine();
            Campaign campaign = findCampaignByTitle(title);

            if (campaign != null) {
                campaigns.remove(campaign);
                System.out.println("Campaign deleted successfully.");
            } else {
                System.out.println("Campaign not found.");
            }
        }

        private void viewCampaigns() {
            System.out.println("\n=== Campaign List ===");
            for (Campaign campaign : campaigns) {
                System.out.println(campaign);
            }
        }

        private void sendCampaigns(CustomerManager customerManager) {
            if (customerManager.customers.isEmpty()) {
                System.out.println("No customers to send campaigns to.");
                return;
            }

            if (campaigns.isEmpty()) {
                System.out.println("No campaigns to send.");
                return;
            }

            System.out.println("Sending campaigns to customers...");
            for (Customer customer : customerManager.customers) {
                for (Campaign campaign : campaigns) {
                    System.out.println("Sent '" + campaign.getTitle() + "' to " + customer.getEmail() + " (Scheduled: " + campaign.getScheduleDate() + ")");
                }
            }
        }

        private Campaign findCampaignByTitle(String title) {
            for (Campaign campaign : campaigns) {
                if (campaign.getTitle().equals(title)) {
                    return campaign;
                }
            }
            return null;
        }
    }

    // Enhanced report generation
    class ReportManager {
        public void viewReports(CampaignManager campaignManager) {
            System.out.println("\n=== Report ===");

            if (campaignManager.campaigns.isEmpty()) {
                System.out.println("No campaigns to report.");
                return;
            }

            for (Campaign campaign : campaignManager.campaigns) {
                System.out.println("Campaign: " + campaign.getTitle() + " | Scheduled: " + campaign.getScheduleDate() + " | Sent to customers.");
            }
        }
    }

    // Customer class with setter methods
    class Customer {
        private String name;
        private String email;

        public Customer(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return "Customer: " + name + ", Email: " + email;
        }
    }

    // Campaign class with added schedule date
    class Campaign {
        private String title;
        private String message;
        private String scheduleDate;

        public Campaign(String title, String message, String scheduleDate) {
            this.title = title;
            this.message = message;
            this.scheduleDate = scheduleDate;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getScheduleDate() {
            return scheduleDate;
        }

        public void setScheduleDate(String scheduleDate) {
            this.scheduleDate = scheduleDate;
        }

        @Override
        public String toString() {
            return "Campaign: " + title + " - " + message + " (Scheduled: " + scheduleDate + ")";
        }
    }
}
