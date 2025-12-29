// Login Page JavaScript
document.addEventListener('DOMContentLoaded', function() {
    // Form handling
    const loginForm = document.querySelector('form');
    const submitBtn = document.querySelector('.btn-primary');
    const btnText = document.querySelector('.btn-text');

    if (loginForm && submitBtn) {
        loginForm.addEventListener('submit', function(e) {
            // Show loading state
            submitBtn.classList.add('loading');
            btnText.textContent = 'Signing In...';

            // Don't disable the actual form inputs - they need to be submitted
            // Only disable non-essential inputs like hidden fields
            const inputs = loginForm.querySelectorAll('input[type="hidden"]');
            inputs.forEach(input => input.disabled = true);
        });
    }

    // Password visibility toggle (optional enhancement)
    const passwordInput = document.getElementById('password');
    if (passwordInput) {
        // Add password toggle button
        const inputWrapper = passwordInput.parentElement;
        const toggleBtn = document.createElement('button');
        toggleBtn.type = 'button';
        toggleBtn.className = 'password-toggle';
        toggleBtn.innerHTML = '👁️';
        toggleBtn.setAttribute('aria-label', 'Toggle password visibility');

        inputWrapper.appendChild(toggleBtn);

        toggleBtn.addEventListener('click', function() {
            const isVisible = passwordInput.type === 'text';
            passwordInput.type = isVisible ? 'password' : 'text';
            toggleBtn.innerHTML = isVisible ? '👁️' : '🙈';
            toggleBtn.setAttribute('aria-label',
                isVisible ? 'Hide password' : 'Show password');
        });
    }

    // Auto-focus enhancement
    const usernameInput = document.getElementById('username');
    if (usernameInput && !usernameInput.value) {
        usernameInput.focus();
    }

    // Error message auto-hide (optional)
    const errorAlert = document.querySelector('.alert-error');
    if (errorAlert) {
        setTimeout(() => {
            errorAlert.style.transition = 'opacity 0.5s ease-out';
            errorAlert.style.opacity = '0';
            setTimeout(() => {
                if (errorAlert.parentNode) {
                    errorAlert.parentNode.removeChild(errorAlert);
                }
            }, 500);
        }, 5000); // Hide after 5 seconds
    }

    // Success message auto-hide
    const successAlert = document.querySelector('.alert-success');
    if (successAlert) {
        setTimeout(() => {
            successAlert.style.transition = 'opacity 0.5s ease-out';
            successAlert.style.opacity = '0';
            setTimeout(() => {
                if (successAlert.parentNode) {
                    successAlert.parentNode.removeChild(successAlert);
                }
            }, 500);
        }, 3000); // Hide after 3 seconds
    }

    // Form validation enhancement
    const inputs = document.querySelectorAll('input[required]');
    inputs.forEach(input => {
        input.addEventListener('blur', function() {
            validateField(this);
        });

        input.addEventListener('input', function() {
            if (this.classList.contains('invalid')) {
                validateField(this);
            }
        });
    });

    function validateField(field) {
        const value = field.value.trim();
        const isValid = value.length > 0;

        field.classList.toggle('invalid', !isValid);
        field.classList.toggle('valid', isValid);
    }

    // Add CSS for validation states
    const style = document.createElement('style');
    style.textContent = `
        .input-wrapper input.invalid {
            border-color: #ef4444 !important;
            box-shadow: 0 0 0 3px rgba(239, 68, 68, 0.1) !important;
        }
        .input-wrapper input.valid {
            border-color: #10b981 !important;
            box-shadow: 0 0 0 3px rgba(16, 185, 129, 0.1) !important;
        }
        .password-toggle {
            position: absolute;
            right: 1rem;
            top: 50%;
            transform: translateY(-50%);
            background: none;
            border: none;
            cursor: pointer;
            font-size: 1.2rem;
            padding: 0.25rem;
            border-radius: 4px;
            transition: background-color 0.2s ease;
        }
        .password-toggle:hover {
            background-color: rgba(0, 0, 0, 0.05);
        }
        .password-toggle:focus {
            outline: 2px solid var(--primary-color);
            outline-offset: 2px;
        }
    `;
    document.head.appendChild(style);

    // Add smooth scrolling for mobile
    if ('scrollBehavior' in document.documentElement.style === false) {
        // Polyfill for smooth scrolling
        const script = document.createElement('script');
        script.src = 'https://unpkg.com/smoothscroll-polyfill@0.4.4/dist/smoothscroll.min.js';
        document.head.appendChild(script);
    }
});

// Handle browser back/forward navigation
window.addEventListener('pageshow', function(event) {
    if (event.persisted) {
        // Page was restored from bfcache, reset form state
        const submitBtn = document.querySelector('.btn-primary');
        const btnText = document.querySelector('.btn-text');
        const inputs = document.querySelectorAll('input');

        if (submitBtn) {
            submitBtn.classList.remove('loading');
            btnText.textContent = 'Sign In';
        }

        inputs.forEach(input => input.disabled = false);
    }
});
