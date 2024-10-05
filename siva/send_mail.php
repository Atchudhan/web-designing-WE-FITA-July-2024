<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
  // Collect form data
  $name = htmlspecialchars(trim($_POST['name']));
  $email = filter_var(trim($_POST['email']), FILTER_SANITIZE_EMAIL);
  $message = htmlspecialchars(trim($_POST['message']));

  // Validate email
  if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
    echo "Invalid email format.";
    exit;
  }

  // Set the recipient email address
  $to = "snsiva854@gmail.com";  // Replace with your email address
  $subject = "New Contact Form Submission from " . $name;

  // Set email headers
  $headers = "From: " . $email . "\r\n" .
             "Reply-To: " . $email . "\r\n" .
             "X-Mailer: PHP/" . phpversion();

  // Create the email body
  $email_body = "You have received a new message from the contact form on your website.\n\n";
  $email_body .= "Name: $name\n";
  $email_body .= "Email: $email\n";
  $email_body .= "Message:\n$message\n";

  // Send the email using the PHP mail() function
  if (mail($to, $subject, $email_body, $headers)) {
    // Redirect to thank-you page after successful submission
    header("Location: thank_you.html");
    exit;
  } else {
    echo "Sorry, there was a problem sending your message.";
  }
} else {
  echo "Invalid request.";
}
?>
