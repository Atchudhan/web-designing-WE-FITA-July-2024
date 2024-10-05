// Select all elements you want to animate on scroll
const scrollElements = document.querySelectorAll('.scroll-animate');

// Function to check if element is in view
const elementInView = (el, threshold = 1) => {
  const elementTop = el.getBoundingClientRect().top;
  return (
    elementTop <= (window.innerHeight || document.documentElement.clientHeight) * threshold
  );
};

// Function to add class if element is in view
const displayScrollElement = (element) => {
  element.classList.add('active');
};

// Function to handle scroll event
const handleScrollAnimation = () => {
  scrollElements.forEach((el) => {
    if (elementInView(el, 0.8)) {  // 0.8 means it triggers when 80% in view
      displayScrollElement(el);
    }
  });
};

// Listen for scroll event
window.addEventListener('scroll', () => {
  handleScrollAnimation();
});
