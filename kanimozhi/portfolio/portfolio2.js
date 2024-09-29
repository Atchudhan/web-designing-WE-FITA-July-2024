/================ toggle icon navbar ================/
let menuIcon = document.querySelector('#menu-icon');
let navbar = document.querySelector('.navbar');

menuIcon.onclick = () => {
    menuIcon.classList.toggle('fa-xmark');
    navbar.classList.toggle('active');
};

/================ scroll section active link================/
let sections = document.querySelectorAll('section');
let navLinks = document.querySelectorAll('header nav a');

window.onscroll = () => {
    sections.forEach(sec => {
        let top = window.scrollY;
        let offset = sec.offsetTop - 150;
        let height = sec.offsetHeight;
        let id = sec.getAttribute('id');

        if (top >= offset && top < offset + height) {
            navLinks.forEach(link => {
                link.classList.remove('active');
                if (link.getAttribute('href').includes(id)) {
                    link.classList.add('active');
                }
            });
        }
    });

    /================ sticky navbar ===========/
    let header = document.querySelector('header');
    header.classList.toggle('sticky', window.scrollY > 100);

    /================ remove toggle icon and navbar ================/
    menuIcon.classList.remove('fa-xmark');
    navbar.classList.remove('active');
};

/================ scroll reveal ================/
ScrollReveal({
    distance: '80px',
    duration: 2000,
    delay: 200,
});

ScrollReveal().reveal('.home-content, .heading', { origin: 'top' });
ScrollReveal().reveal('.home-img, .services-container, .portfolio-box, .contact form', { origin: 'bottom' });
ScrollReveal().reveal('.home-content h1, .about-img', { origin: 'left' });
ScrollReveal().reveal('.home-content p, .about-content', { origin: 'right' });

/================ Typed Js ================/
const typed = new Typed('.multiple-text', {
    strings: ['Frontend Developer', 'Web Designer', 'Web Developer'],
    typeSpeed: 70,
    backSpeed: 70,
    backDelay: 1000,
    loop: true,
});
/================ for 3d ================/
document.querySelectorAll('.know-more-btn').forEach(button => {
    button.addEventListener('click', () => {
        const additionalInfo = button.nextElementSibling; // Get the next sibling (additional info)

        if (additionalInfo.classList.contains('show')) {
            additionalInfo.classList.remove('show'); // Hide the info
        } else {
            additionalInfo.classList.add('show'); // Show the info
        }
    });
});