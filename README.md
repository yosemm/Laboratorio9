# Laboratorio 9 - MVVM con Jetpack Compose
Se realizó un proyecto en Android que implementa el patrón MVVM (Model-View-ViewModel) con Jetpack Compose para gestionar una Lista de Deseos. 
El proyecto demuestra el manejo de estado con StateFlow, navegación entre pantallas y persistencia de datos durante cambios de configuración.

## Video de funcionamiento final



https://github.com/user-attachments/assets/274c0867-e5b8-4936-b834-4c5f054f5552



## Arquitectura MVVM

### Diagrama de Capas
UI Layer (Composables)
↓ eventos
ViewModel (WishlistViewModel)
↓ usa
Domain Layer (Product, WishlistUiState)

### Explicación

**Model (Domain):**
- `Product`: Representa un producto con id, nombre y estado de favorito
- `WishlistUiState`: Estado inmutable que contiene la lista de productos

**ViewModel:**
- `WishlistViewModel`: Gestiona el estado de la UI mediante StateFlow
- Expone `uiState` como única fuente de verdad
- Procesa eventos de la UI (toggleWishlist, loadProducts)
- Sobrevive a cambios de configuración

**View (UI):**
- `WishlistScreen`: Observa el estado con `collectAsStateWithLifecycle()`
- `ProfileScreen`: Comparte el mismo ViewModel para mantener consistencia
- Componentes puros que solo renderizan y emiten callbacks

### Flujo Unidireccional de Datos (UDF)
UI → Evento → ViewModel → StateFlow → UI

## Estructura del Proyecto
```
app/src/main/java/com/example/lab9/
├── feature/
│   ├── wishlist/
│   │   ├── domain/
│   │   │   └── model/
│   │   │       ├── Product.kt
│   │   │       └── WishlistUiState.kt
│   │   └── presentation/
│   │       ├── WishlistViewModel.kt
│   │       ├── WishlistScreen.kt
│   │       └── components/
│   │           └── ProductItem.kt
│   └── profile/
│       └── presentation/
│           └── ProfileScreen.kt
├── navigation/
│   ├── AppNavigation.kt
│   └── Destinations.kt
├── ui/
│   └── theme/
├── MainActivity.kt
└── LikeCounterScreen.kt
```
---

###  Evidenciar que el estado con remember no sobrevive cambios de configuración (Video de la Parte 1): LikeCounterScreen

[EvidenciaParte1.webm](https://github.com/user-attachments/assets/395c7c3e-fd45-406c-ac2f-52aad12e69c4)


## Reflexiones

### Reflexión 1: ¿Por qué el estado no persiste al rotar?

El estado con `remember` no persiste al rotar porque:

1. **Recreación de la Activity**: Android destruye y recrea la Activity cuando cambia la configuración (rotación)
2. **Alcance de remember**: `remember` solo mantiene el estado durante la composición actual, no sobrevive a la recreación de la Activity
3. **Nuevo árbol de composición**: Al recrearse la Activity, se construye un nuevo árbol de Composables desde cero

---

### Reflexión 2: ¿Por qué el ViewModel resuelve el problema de la Parte 1?

El ViewModel resuelve el problema porque:

1. **Ciclo de vida extendido**: El ViewModel sobrevive a cambios de configuración como rotaciones
2. **Única fuente de verdad**: Todo el estado de la UI está centralizado en el ViewModel, no disperso en composables
3. **Separación de responsabilidades**: La lógica de negocio está en el ViewModel, la UI solo renderiza
4. **StateFlow**: Permite observar cambios de estado de forma reactiva y lifecycle-aware con `collectAsStateWithLifecycle()`

---

### Reflexión 3: Ventajas de compartir ViewModel vs pasar argumentos de navegación

**Ventajas de compartir un ViewModel entre destinos:**

1. **Fuente única de verdad**: Todas las pantallas leen del mismo estado, evitando inconsistencias
2. **Sincronización automática**: Los cambios en una pantalla se reflejan inmediatamente en otras
3. **Sin límites de tamaño**: Los argumentos de navegación tienen límite (~500KB), el ViewModel no
4. **Objetos complejos**: Se pueden compartir listas y objetos sin serialización
5. **Comunicación bidireccional**: Cualquier pantalla puede modificar el estado compartido

**Desventajas de pasar argumentos:**
- Solo admite tipos primitivos y Parcelables
- Requiere serialización/deserialización
- Comunicación unidireccional
- Propenso a errores con datos complejos

---

## Autores

Andrés Ismalej 24005, Denil Parada 24761 y Jorge Chupina 22213
