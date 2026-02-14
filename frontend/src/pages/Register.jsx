import { useState } from 'react';
import { useAuth } from '../context/AuthContext';
import { useNavigate, Link } from 'react-router-dom';

export function Register() {
    const [formData, setFormData] = useState({
        username: '',
        firstname: '',
        lastname: '',
        email: '',
        password: '',
        confirmPassword: ''
    });
    const [error, setError] = useState('');
    const { register } = useAuth();
    const navigate = useNavigate();

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
        if (error) setError('');
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        if (formData.password !== formData.confirmPassword) {
            setError('Passwords do not match');
            return;
        }

        register(formData);
        navigate('/profile');
    };

    return (
        <div className="container py-5 animate-fade-in d-flex align-items-center justify-content-center" style={{ minHeight: '80vh' }}>
            <div className="card shadow-sm border-0 p-4" style={{ maxWidth: '400px', width: '100%' }}>
                <h2 className="text-center fw-bold mb-4">Join CookShare</h2>
                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label className="form-label fw-semibold">Username</label>
                        <input
                            type="text"
                            className="form-control"
                            name="username"
                            placeholder="coolchef123"
                            value={formData.username}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div className="row g-2 mb-3">
                        <div className="col-6">
                            <label className="form-label fw-semibold">First Name</label>
                            <input
                                type="text"
                                className="form-control"
                                name="firstname"
                                placeholder="John"
                                value={formData.firstname}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <div className="col-6">
                            <label className="form-label fw-semibold">Last Name</label>
                            <input
                                type="text"
                                className="form-control"
                                name="lastname"
                                placeholder="Doe"
                                value={formData.lastname}
                                onChange={handleChange}
                                required
                            />
                        </div>
                    </div>
                    <div className="mb-3">
                        <label className="form-label fw-semibold">Email</label>
                        <input
                            type="email"
                            className="form-control"
                            name="email"
                            placeholder="you@example.com"
                            value={formData.email}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div className="mb-3">
                        <label className="form-label fw-semibold">Password</label>
                        <input
                            type="password"
                            className="form-control"
                            name="password"
                            placeholder="••••••••"
                            value={formData.password}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div className="mb-3">
                        <label className="form-label fw-semibold">Repeat Password</label>
                        <input
                            type="password"
                            className={`form-control ${error ? 'is-invalid' : ''}`}
                            name="confirmPassword"
                            placeholder="••••••••"
                            value={formData.confirmPassword}
                            onChange={handleChange}
                            required
                        />
                        {error && <div className="invalid-feedback">{error}</div>}
                    </div>
                    <button type="submit" className="btn btn-primary w-100 py-2 mb-3">Sign Up</button>
                </form>
                <p className="text-center text-muted small mb-0">
                    Already have an account? <Link to="/login" className="text-primary text-decoration-none fw-semibold">Log In</Link>
                </p>
            </div>
        </div>
    );
}
