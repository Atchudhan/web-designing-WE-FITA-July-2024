/*================ toggle icon navbar ================*/
let menuIcon = document.querySelector('#menu-icon');
let navbar = document.querySelector('.navbar');

menuIcon.onclick = () => {
    menuIcon.classList.toggle('fa-xmark');
    navbar.classList.toggle('active');
};

/*================ scroll section active link================*/
let sections = document.querySelectorAll('section');
let navLinks = document.querySelectorAll('header nav a');

window.onscroll = () => {
    sections.forEach(sec => {
        let top = window.scrollY;
        let offset = sec.offsetTop - 150;
        let height = sec.offsetHeight;
        let id = sec.getAttribute('id');

        if (top >= offset && top < offset + height) {
            navLinks.forEach.apply(link => {
                link.classList.remove('active');
                document.querySelector('header nav a[href*=' + id + ']').classList.add('active');
                /*================ sticky navbar 
                if (link.getAttribute('href').includes(id)) {
                    link.classList.add('active');
                }===========*/
            });
        }
    });

    /*================ sticky navbar ===========*/
    let header = document.querySelector('header');
    header.classList.toggle('sticky', window.scrollY > 100);

    /*================ remove toggle icon and navbar ================*/
    menuIcon.classList.remove('fa-xmark');
    navbar.classList.remove('active');
};

/*================ scroll reveal ================*/
ScrollReveal({
    distance: '80px',
    duration: 2000,
    delay: 200,
});

ScrollReveal().reveal('.home-content, .heading', { origin: 'top' });
ScrollReveal().reveal('.home-img, .services-container, .portfolio-box, .contact form', { origin: 'bottom' });
ScrollReveal().reveal('.home-content h1, .about-img', { origin: 'left' });
ScrollReveal().reveal('.home-content p, .about-content', { origin: 'right' });

/*================ Typed Js ================*/
const typed = new Typed('.multiple-text', {
    strings: ['Frontend Developer', 'Web Designer', 'Web Developer'],
    typeSpeed: 70,
    backSpeed: 70,
    backDelay: 1000,
    loop: true,
});

// Initial setup for light mode as default
document.body.classList.add('dark-mode');

// Dark/Light Mode Toggle
const darkLightBtn = document.querySelector('#dark-light-btn');
const body = document.body;

darkLightBtn.onclick = () => {
    // Toggle between dark-mode and light-mode classes
    if (body.classList.contains('light-mode')) {
        body.classList.remove('light-mode');
        body.classList.add('dark-mode');
        darkLightBtn.innerHTML = '🌙'; // Change to moon icon for dark mode
    } else {
        body.classList.remove('dark-mode');
        body.classList.add('light-mode');
        darkLightBtn.innerHTML = '☀️'; // Change to sun icon for light mode
    }
};

// Color Mode Toggle
const colorBtn = document.querySelector('#color-mode-btn');
const colorOptions = document.querySelector('#color-options');
const colors = ['blue-theme', 'green-theme', 'red-theme', 'purple-theme', 'orange-theme', 'teal-theme'];
let currentColorIndex = 0;

// Show color options on button click
colorBtn.onclick = () => {
    colorOptions.classList.toggle('active');
};

// Handle color box click
document.querySelectorAll('.color-box').forEach((box, index) => {
    box.addEventListener('click', () => {
        // Remove current color theme
        colors.forEach(color => body.classList.remove(color));

        // Apply selected color theme
        body.classList.add(colors[index]);

        // Optionally update the button text or icon
        colorBtn.innerHTML = `Change Color: ${colors[index].split('-')[0]}`;
    });
});
